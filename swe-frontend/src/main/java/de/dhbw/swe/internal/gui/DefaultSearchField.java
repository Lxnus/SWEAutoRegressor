package de.dhbw.swe.internal.gui;

import com.google.inject.Inject;
import de.dhbw.swe.main.gui.Component;
import de.dhbw.swe.main.gui.SearchField;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DefaultSearchField implements SearchField {

    private final int rows;

    private final Component.Factory componentFactory;

    private final HashMap<String, Component> searchComponents;

    @Inject
    public DefaultSearchField(Component.Factory componentFactory) {
        this.componentFactory = componentFactory;

        this.searchComponents = new HashMap<>();
        this.rows = 1;
    }

    @Override
    public HashMap<String, Component> getSearchComponents() {
        return this.searchComponents;
    }

    @Override
    public void addSearchComponent(String componentName, String componentUnit) {
        Component component = this.componentFactory.create(componentName, componentUnit);
        this.searchComponents.put(componentName, component);
    }

    @Override
    public void removeSearchComponent(String componentName) {
        this.searchComponents.remove(componentName);
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
        buttonPanel.add(searchButton);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchComponentsPanel, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.SOUTH);

        return searchPanel;
    }
}
