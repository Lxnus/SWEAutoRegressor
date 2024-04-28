package de.dhbw.swe.implementation.gui.search_component;

import javax.swing.*;

public class NumberComponent extends JPanel implements SearchComponent {

    private final String name;
    private final String unit;
    private final JSpinner inputField;

    public NumberComponent(String name, String unit) {
        this.name = name;
        this.unit = unit;
        this.inputField = new JSpinner();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel(name));
        this.add(inputField);

        if (!unit.isEmpty())
            this.add(new JLabel(unit));
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public JSpinner getInputField() {
        return inputField;
    }

    public JPanel get() {
        return this;
    }
}
