package de.dhbw.swe.implementation.gui.search_component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchComponentsManager {
    private List<SearchComponent> searchComponents = new ArrayList<>();
    private int rows = 1;

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
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(this.rows, (int) Math.ceil((double) (this.getSearchComponentsSize() + 1) / this.rows)));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        for(SearchComponent searchComponent : this.getSearchComponents()) {


            searchComponent.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
            searchPanel.add(searchComponent.get());
        }


        searchPanel.add(new JButton("Suchen"));
    }
}
