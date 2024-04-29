package de.dhbw.swe.main.database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DatabaseConfigurationTest {

    private DatabaseConfiguration databaseConfiguration;

    @Before
    public void setUp() {
        databaseConfiguration = Mockito.mock(DatabaseConfiguration.class);
    }

    @Test
    public void testGetDatabaseUser() {
        databaseConfiguration.getDatabaseUser();
        Mockito.verify(databaseConfiguration).getDatabaseUser();
    }

    @Test
    public void testGetDatabasePassword() {
        databaseConfiguration.getDatabasePassword();
        Mockito.verify(databaseConfiguration).getDatabasePassword();
    }

    @Test
    public void testGetDatabaseUrl() {
        databaseConfiguration.getDatabaseUrl();
        Mockito.verify(databaseConfiguration).getDatabaseUrl();
    }

    @Test
    public void testGetDatabaseDriver() {
        databaseConfiguration.getDatabaseDriver();
        Mockito.verify(databaseConfiguration).getDatabaseDriver();
    }
}
