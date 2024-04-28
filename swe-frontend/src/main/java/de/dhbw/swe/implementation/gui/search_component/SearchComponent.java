package de.dhbw.swe.implementation.gui.search_component;

import javax.swing.*;

public interface SearchComponent<T extends JPanel> {

    default void defaultConfigure() {
        ((T) this).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
    }
    String getName();
    JComponent get();
}
