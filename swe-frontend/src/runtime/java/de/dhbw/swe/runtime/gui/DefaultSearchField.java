package de.dhbw.swe.runtime.gui;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.main.grpc.services.LinearRegressionService;
import de.dhbw.swe.main.gui.InputSearchComponent;
import de.dhbw.swe.main.gui.SearchField;
import de.dhbw.swe.main.sampler.Sampler;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.main.sampler.SamplingInterval;
import de.dhbw.swe.runtime.inject.AutoBind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@AutoBind(SearchField.class)
public class DefaultSearchField implements SearchField {

    private final int rows;

    private final Sampler sampler;
    private final InputSearchComponent.Factory componentFactory;
    private final SamplingInterval.Factory samplingIntervalFactory;
    private final SamplingConfiguration.Factory samplingConfigurationFactory;
    private final LinearRegressionService linearRegressionService;

    private final HashMap<String, InputSearchComponent> searchComponents;

    @Inject
    public DefaultSearchField(
            @Assisted("grpcClient") GrpcClient grpcClient,
            Sampler sampler,
            InputSearchComponent.Factory componentFactory,
            SamplingInterval.Factory samplingIntervalFactory,
            SamplingConfiguration.Factory samplingConfigurationFactory,
            LinearRegressionService.Factory linearRegressionServiceFactory
    ) {
        this.sampler = sampler;
        this.componentFactory = componentFactory;
        this.samplingIntervalFactory = samplingIntervalFactory;
        this.samplingConfigurationFactory = samplingConfigurationFactory;

        this.linearRegressionService = linearRegressionServiceFactory.create(grpcClient);

        this.searchComponents = new HashMap<>();
        this.rows = 1;
    }

    @Override
    public HashMap<String, InputSearchComponent> getSearchComponents() {
        return this.searchComponents;
    }

    @Override
    public void addInputSearchComponent(String componentName, String componentUnit, int minInterval, int maxInterval) {
        InputSearchComponent inputSearchComponent = this.componentFactory.create(componentName, componentUnit);
        this.searchComponents.put(componentName, inputSearchComponent);

        SamplingInterval samplingInterval = this.samplingIntervalFactory.create(minInterval, maxInterval);
        SamplingConfiguration samplingConfiguration = this.samplingConfigurationFactory.create(samplingInterval);
        this.sampler.addCategory(componentName, samplingConfiguration);
    }

    @Override
    public void removeInputSearchComponent(String componentName) {
        this.searchComponents.remove(componentName);

        this.sampler.removeCategory(componentName);
    }

    @Override
    public InputSearchComponent getInputSearchComponent(String name) {
        if (!this.searchComponents.containsKey(name)) {
            return null;
        }
        return this.searchComponents.get(name);
    }

    @Override
    public void clearInputSearchComponents() {
        this.searchComponents.clear();
        this.sampler.clearCategories();
    }

    @Override
    public JComponent getSearchField() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(this.createSearchComponentsPanel(), BorderLayout.CENTER);
        searchPanel.add(this.createButtonPanel(), BorderLayout.SOUTH);

        return searchPanel;
    }

    private JPanel createSearchComponentsPanel() {
        JPanel searchComponentsPanel = new JPanel();
        searchComponentsPanel.setLayout(new GridLayout(this.rows, (int) Math.ceil((double) this.getSearchComponents().size() / this.rows)));
        searchComponentsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        for (InputSearchComponent searchInputSearchComponent : this.searchComponents.values()) {
            JComponent component = searchInputSearchComponent.getComponent();
            component.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
            searchComponentsPanel.add(component);
        }

        return searchComponentsPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton searchButton = new JButton("Suchen");
        searchButton.addActionListener(this::actionPerformed);
        buttonPanel.add(searchButton);

        return buttonPanel;
    }

    public void actionPerformed(ActionEvent e) {
        HashMap<String, Double> searchValues = new HashMap<>();
        for (InputSearchComponent inputSearchComponent : searchComponents.values()) {
            double value;
            try {
                value = Double.parseDouble(inputSearchComponent.getInputField().getValue().toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie nur Zahlen ein.");
                return;
            }
            searchValues.put(inputSearchComponent.getComponentName(), value);
        }
        List<Double> representationValues = sampler.sample(searchValues,10);
        List<Double> yValues = sampler.sample(searchValues, 10);
        long uuid = new Random().nextLong();

        double meanRepresentationValue = representationValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        linearRegressionService.create(uuid, representationValues, yValues);
        double prediction = linearRegressionService.predict(meanRepresentationValue, uuid);
        JOptionPane.showMessageDialog(null, "Vorhersage: " + Math.round(prediction) + " Euro", "Vorhersage", JOptionPane.INFORMATION_MESSAGE);
    }
}
