package de.dhbw.swe.main.gui;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.inject.AssistedFactory;

import javax.swing.*;

@AssistedFactory(Component.Factory.class)
public interface Component {

    String getComponentName();

    String getComponentUnit();

    JComponent getComponent();

    JSpinner getInputField();

    interface Factory {
        Component create(@Assisted("name") String name, @Assisted("unit") String unit);
    }
}
