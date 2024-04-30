package de.dhbw.swe.main.gui;

import de.dhbw.swe.main.grpc.GrpcClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SearchFieldTest {

    private SearchField searchField;

    @Before
    public void setUp() {
        GrpcClient grpcClient = Mockito.mock(GrpcClient.class);
        SearchField.Factory searchFieldFactory = Mockito.mock(SearchField.Factory.class);
        this.searchField = searchFieldFactory.create(grpcClient);
        System.out.println(this.searchField);
    }

    @Test
    public void testGetInputSearchComponents() {
        searchField.getSearchComponents();
        Mockito.verify(searchField).getSearchComponents();
    }

    @Test
    public void testAddInputSearchComponent() {
        searchField.addInputSearchComponent("ComponentName", "ComponentUnit", 0, 10);

        assert searchField.getInputSearchComponent("ComponentName") != null;
        assert searchField.getInputSearchComponent("ComponentName").getComponentUnit().equalsIgnoreCase("ComponentUnit");
        assert searchField.getInputSearchComponent("ComponentName").getComponentName().equalsIgnoreCase("ComponentName");

        Mockito.verify(searchField).addInputSearchComponent("ComponentName", "ComponentUnit", 0, 10);
    }

}
