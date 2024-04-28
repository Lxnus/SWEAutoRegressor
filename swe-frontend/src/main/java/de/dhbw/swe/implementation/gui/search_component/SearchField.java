package de.dhbw.swe.implementation.gui.search_component;

import javax.swing.*;

public class SearchField {

    private final SearchFieldGenerator searchFieldGenerator;

    public SearchField() {
        this.searchFieldGenerator = new SearchFieldGenerator();

        this.addSearchComponents();
    }

    private void addSearchComponents() {
        this.searchFieldGenerator.add(new NumberComponent("Kilometerstand", "km"));
        this.searchFieldGenerator.add(new NumberComponent("Baujahr", ""));
        this.searchFieldGenerator.add(new NumberComponent("Leistung", "PS"));
    }

    public JPanel get() {
        return this.searchFieldGenerator.getSearchPanel();
    }
}
