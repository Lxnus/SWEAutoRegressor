package de.dhbw.swe.main.gui;

import com.google.inject.ImplementedBy;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.internal.gui.DefaultApplication;

import javax.swing.*;

@ImplementedBy(DefaultApplication.class)
public interface Application {

    void start();

    void stop();

    String getApplicationName();

    int getApplicationWidth();

    int getApplicationHeight();

    JFrame getApplication();

    SearchField getSearchField();

    interface Factory {
        Application create(@Assisted("windowTitle") String windowTitle,
                           @Assisted("windowWidth") int windowWidth,
                           @Assisted("windowHeight") int windowHeight);
    }
}
