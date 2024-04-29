package de.dhbw.swe.main.gui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ApplicationTest {

    private Application application;

    @Before
    public void setUp() {
        application = Mockito.mock(Application.class);
    }

    @Test
    public void testStart() {
        application.start();
        Mockito.verify(application).start();
    }

    @Test
    public void testStop() {
        application.stop();
        Mockito.verify(application).stop();
    }

    @Test
    public void testGetApplicationName() {
        application.getApplicationName();
        Mockito.verify(application).getApplicationName();
    }

    @Test
    public void testGetApplicationWidth() {
        application.getApplicationWidth();
        Mockito.verify(application).getApplicationWidth();
    }

    @Test
    public void testGetApplicationHeight() {
        application.getApplicationHeight();
        Mockito.verify(application).getApplicationHeight();
    }

    @Test
    public void getApplication() {
        application.getApplication();
        Mockito.verify(application).getApplication();
    }

    @Test
    public void testGetSearchField() {
        application.getSearchField();
        Mockito.verify(application).getSearchField();
    }

    @Test
    public void testFactory() {
        Application.Factory factory = Mockito.mock(Application.Factory.class);
        Application application = factory.create("windowTitle", 0, 0);
        Mockito.verify(factory).create("windowTitle", 0, 0);
    }
}
