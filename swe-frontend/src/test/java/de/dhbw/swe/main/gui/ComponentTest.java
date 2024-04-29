package de.dhbw.swe.main.gui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ComponentTest {

    private Component component;

    @Before
    public void setUp() {
        component = Mockito.mock(Component.class);
    }

    @Test
    public void testGetComponentName() {
        component.getComponentName();
        Mockito.verify(component).getComponentName();
    }

    @Test
    public void testGetComponentUnit() {
        component.getComponentUnit();
        Mockito.verify(component).getComponentUnit();
    }

    @Test
    public void testGetComponent() {
        component.getComponent();
        Mockito.verify(component).getComponent();
    }

    @Test
    public void testGetInputField() {
        component.getInputField();
        Mockito.verify(component).getInputField();
    }

    @Test
    public void testFactory() {
        Component.Factory factory = Mockito.mock(Component.Factory.class);
        Component component = factory.create("ComponentName", "ComponentUnit");
        Mockito.verify(factory).create("ComponentName", "ComponentUnit");
    }
}
