package de.dhbw.swe.internal.gui;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.gui.Component;

import javax.inject.Inject;
import javax.swing.*;

public class DefaultComponent extends JPanel implements Component {

    private final String name;
    private final String unit;

    private final JSpinner inputField;

    @Inject
    public DefaultComponent(@Assisted("name") String name, @Assisted("unit") String unit) {
        this.name = name;
        this.unit = unit;
        this.inputField = new JSpinner();

        initialize();
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel(name));
        this.add(inputField);

        if (!unit.isEmpty())
            this.add(new JLabel(unit));
    }

    @Override
    public String getComponentName() {
        return this.name;
    }

    @Override
    public String getComponentUnit() {
        return this.unit;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public JComponent getInputField() {
        return this.inputField;
    }
}
