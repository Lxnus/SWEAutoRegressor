package de.dhbw.swe.main.gui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class InputSearchComponentTest {

    private InputSearchComponent inputSearchComponent;

    @Before
    public void setUp() {
        inputSearchComponent = Mockito.mock(InputSearchComponent.class);
    }

    @Test
    public void testGetComponentName() {
        inputSearchComponent.getComponentName();
        Mockito.verify(inputSearchComponent).getComponentName();
    }

    @Test
    public void testGetComponentUnit() {
        inputSearchComponent.getComponentUnit();
        Mockito.verify(inputSearchComponent).getComponentUnit();
    }

    @Test
    public void testGetComponent() {
        inputSearchComponent.getComponent();
        Mockito.verify(inputSearchComponent).getComponent();
    }

    @Test
    public void testGetInputField() {
        inputSearchComponent.getInputField();
        Mockito.verify(inputSearchComponent).getInputField();
    }

    @Test
    public void testFactory() {
        InputSearchComponent.Factory factory = Mockito.mock(InputSearchComponent.Factory.class);
        InputSearchComponent inputSearchComponent = factory.create("ComponentName", "ComponentUnit");
        Mockito.verify(factory).create("ComponentName", "ComponentUnit");
    }
}
