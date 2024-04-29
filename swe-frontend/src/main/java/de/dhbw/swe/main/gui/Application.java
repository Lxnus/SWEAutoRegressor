package de.dhbw.swe.main.gui;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.inject.AssistedFactory;

import javax.swing.*;

@AssistedFactory(Application.Factory.class)
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
