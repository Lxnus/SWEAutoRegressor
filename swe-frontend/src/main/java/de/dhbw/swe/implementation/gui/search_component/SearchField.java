package de.dhbw.swe.implementation.gui.search_component;

import javax.swing.*;
import java.awt.*;

public class SearchField extends JPanel {
    private SearchComponentsManager searchComponentsManager;
    private final int rows = 1;

    public SearchField() {
        this.searchComponentsManager = new SearchComponentsManager();

        this.addSearchComponents();

        this.configure();
    }

    private void addSearchComponents() {
        this.searchComponentsManager.add(new NumberComponent("Kilometerstand", "km"));
        this.searchComponentsManager.add(new NumberComponent("Baujahr", ""));
        this.searchComponentsManager.add(new NumberComponent("Leistung", "PS"));
    }

    private void configure() {
        this.setLayout(new GridLayout(this.rows, (int) Math.ceil((double) (this.searchComponentsManager.getSearchComponentsSize() + 1) / this.rows)));
        this.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        for(SearchComponent searchComponent : this.searchComponentsManager.getSearchComponents()) {
            this.add(searchComponent.get());
        }


        this.add(new JButton("Suchen"));
    }
}
