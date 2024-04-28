package de.dhbw.swe.implementation.gui.search_component;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SearchFieldGenerator {

    private LinkedList<SearchComponent> searchComponents;
    private int rows;

    public SearchFieldGenerator() {
        this.searchComponents = new LinkedList<>();
        this.rows = 1;
    }

    public void add(SearchComponent searchComponent) {
        this.searchComponents.add(searchComponent);
    }

    public List<SearchComponent> getSearchComponents() {
        return this.searchComponents;
    }

    private int getSearchComponentsSize() {
        return this.searchComponents.size();
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public JPanel getSearchPanel() {
        JPanel searchComponentsPanel = new JPanel();
        searchComponentsPanel.setLayout(new GridLayout(this.rows, (int) Math.ceil((double) this.getSearchComponentsSize() / this.rows)));
        searchComponentsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        for(SearchComponent searchComponent : this.getSearchComponents()) {
            JComponent component = searchComponent.get();
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
