package de.dhbw.swe.implementation;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
        this.configure();
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 2));
        JComboBox<String> carBrand = new JComboBox<>();
        carBrand.addItem("Audi");
        carBrand.addItem("BMW");
        carBrand.addItem("Mercedes");
        carBrand.addItem("Volkswagen");
        searchPanel.add(carBrand);
        JSpinner carBuildYear = new JSpinner();
        searchPanel.add(carBuildYear);
        JSpinner carMileage = new JSpinner();
        searchPanel.add(carMileage);
        JSpinner carPS = new JSpinner();
        searchPanel.add(carPS);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        this.add(searchPanel);
        this.setVisible(true);
    }


    private void configure() {
        this.setTitle("Esti-Mate");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
