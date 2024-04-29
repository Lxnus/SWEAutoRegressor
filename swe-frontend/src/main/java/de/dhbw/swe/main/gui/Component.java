package de.dhbw.swe.main.gui;

import com.google.inject.ImplementedBy;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.internal.gui.DefaultComponent;

import javax.swing.*;

@ImplementedBy(DefaultComponent.class)
public interface Component {

    String getComponentName();

    String getComponentUnit();

    JComponent getComponent();

    JComponent getInputField();

    interface Factory {
        Component create(@Assisted("name") String name, @Assisted("unit") String unit);
    }
}
