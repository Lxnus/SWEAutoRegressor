package de.dhbw.swe.implementation.gui.views;

import de.dhbw.swe.implementation.gui.search_component.SearchField;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        this.setTitle("Esti-Mate");
        this.setSize(900, 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(new SearchField().get(), BorderLayout.CENTER);

        this.setVisible(true);
    }
}
