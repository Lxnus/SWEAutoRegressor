package de.dhbw.swe.internal.gui;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.main.grpc.services.LinearRegressionService;
import de.dhbw.swe.main.gui.Component;
import de.dhbw.swe.main.gui.SearchField;
import de.dhbw.swe.main.sampler.Sampler;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.main.sampler.SamplingInterval;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DefaultSearchField implements SearchField {

    private final int rows;

    private final Sampler sampler;
    private final Component.Factory componentFactory;
    private final SamplingInterval.Factory samplingIntervalFactory;
    private final SamplingConfiguration.Factory samplingConfigurationFactory;
    private final LinearRegressionService linearRegressionService;

    private final HashMap<String, Component> searchComponents;

    @Inject
    public DefaultSearchField(
            @Assisted("grpcClient") GrpcClient grpcClient,
            Sampler sampler,
            Component.Factory componentFactory,
            SamplingInterval.Factory samplingIntervalFactory,
            SamplingConfiguration.Factory samplingConfigurationFactory
    ) {
        this.sampler = sampler;
        this.componentFactory = componentFactory;
        this.samplingIntervalFactory = samplingIntervalFactory;
        this.samplingConfigurationFactory = samplingConfigurationFactory;

        this.linearRegressionService = LinearRegressionService.Factory.create(grpcClient);

        this.searchComponents = new HashMap<>();
        this.rows = 1;
    }

    @Override
    public HashMap<String, Component> getSearchComponents() {
        return this.searchComponents;
    }

    @Override
    public void addSearchComponent(String componentName, String componentUnit, int minInterval, int maxInterval) {
        Component component = this.componentFactory.create(componentName, componentUnit);
        this.searchComponents.put(componentName, component);

        SamplingInterval samplingInterval = this.samplingIntervalFactory.create(minInterval, maxInterval);
        SamplingConfiguration samplingConfiguration = this.samplingConfigurationFactory.create(samplingInterval);
        this.sampler.addCategory(componentName, samplingConfiguration);
    }

    @Override
    public void removeSearchComponent(String componentName) {
        this.searchComponents.remove(componentName);

        this.sampler.removeCategory(componentName);
    }

    @Override
    public Component getSearchComponent(String name) {
        if (!this.searchComponents.containsKey(name)) {
            return null;
        }
        return this.searchComponents.get(name);
    }

    @Override
    public void clearSearchComponents() {
        this.searchComponents.clear();
        this.sampler.clearCategories();
    }

    @Override
    public JComponent getSearchField() {
        JPanel searchComponentsPanel = new JPanel();
        searchComponentsPanel.setLayout(new GridLayout(this.rows, (int) Math.ceil((double) this.getSearchComponents().size() / this.rows)));
        searchComponentsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        for (Component searchComponent : this.searchComponents.values()) {
            JComponent component = searchComponent.getComponent();
            component.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
            searchComponentsPanel.add(component);
        }

        JPanel buttonPanel = new JPanel();
        JButton searchButton = new JButton("Suchen");
        searchButton.addActionListener(e -> {
            HashMap<String, Double> searchValues = new HashMap<>();
            for (Component component : searchComponents.values()) {
                double value;
                try {
                    value = Double.parseDouble(component.getInputField().getValue().toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie nur Zahlen ein.");
                    return;
                }
                searchValues.put(component.getComponentName(), value);
            }
            List<Double> representationValues = sampler.sample(searchValues,10);
            List<Double> yValues = sampler.sample(searchValues, 10);
            long uuid = new Random().nextLong();

            double meanRepresentationValue = representationValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

            linearRegressionService.create(uuid, representationValues, yValues);
            double prediction = linearRegressionService.predict(meanRepresentationValue, uuid);
            JOptionPane.showMessageDialog(null, "Vorhersage: " + Math.round(prediction) + " Euro", "Vorhersage", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(searchButton);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchComponentsPanel, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.SOUTH);

        return searchPanel;
    }
}
