package de.dhbw.swe.main.gui;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.gui.DefaultSearchField;

import javax.swing.*;
import java.util.HashMap;

@ImplementedBy(DefaultSearchField.class)
public interface SearchField {

    HashMap<String, Component> getSearchComponents();

    void addSearchComponent(String componentName, String componentUnit);

    void removeSearchComponent(String componentName);

    void clearSearchComponents();

    Component getSearchComponent(String name);

    JComponent getSearchField();

    interface Factory {
        SearchField create();
    }
}
