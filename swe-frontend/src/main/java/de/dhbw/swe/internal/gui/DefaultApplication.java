package de.dhbw.swe.internal.gui;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.gui.Application;
import de.dhbw.swe.main.gui.SearchField;

import javax.swing.*;
import java.awt.*;

public class DefaultApplication extends JFrame implements Application {

    private final String windowTitle;

    private final int windowWidth;
    private final int windowHeight;

    private final SearchField searchField;

    @Inject
    public DefaultApplication(@Assisted("windowTitle") String windowTitle,
                              @Assisted("windowWidth") int windowWidth,
                              @Assisted("windowHeight") int windowHeight,
                              SearchField searchField) {
        this.windowTitle = windowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.searchField = searchField;
    }

    @Override
    public void start() {
        this.setTitle(this.windowTitle);
        this.setSize(this.windowWidth, this.windowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(this.searchField.getSearchField(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void stop() {
        this.dispose();
    }

    @Override
    public String getApplicationName() {
        return this.windowTitle;
    }

    @Override
    public int getApplicationWidth() {
        return this.windowWidth;
    }

    @Override
    public int getApplicationHeight() {
        return this.windowHeight;
    }

    @Override
    public JFrame getApplication() {
        return this;
    }

    @Override
    public SearchField getSearchField() {
        return searchField;
    }
}
