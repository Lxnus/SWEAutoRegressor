package de.dhbw.swe.main.gui;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.inject.AssistedFactory;

import javax.swing.*;

@AssistedFactory(InputSearchComponent.Factory.class)
public interface InputSearchComponent {

    String getComponentName();

    String getComponentUnit();

    JComponent getComponent();

    JSpinner getInputField();

    interface Factory {
        InputSearchComponent create(@Assisted("name") String name, @Assisted("unit") String unit);
    }
}
