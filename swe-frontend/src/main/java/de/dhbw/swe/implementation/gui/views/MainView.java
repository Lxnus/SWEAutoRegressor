package de.dhbw.swe.implementation.gui.views;

import de.dhbw.swe.implementation.gui.search_component.SearchField;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
        this.configure();

        SearchField searchField = new SearchField();

        this.add(searchField, BorderLayout.CENTER);

        this.setVisible(true);
    }


    private void configure() {
        this.setTitle("Esti-Mate");
        this.setSize(800, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }
}
