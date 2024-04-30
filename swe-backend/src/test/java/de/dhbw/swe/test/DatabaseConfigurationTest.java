package de.dhbw.swe.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.domain.valueObjects.configuration.DatabaseConfiguration;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConfigurationTest {

    private DatabaseConfiguration configuration;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));
        configuration = injector.getInstance(DatabaseConfiguration.class);
    }

    @Test
    public void testDatabase() {
        Assert.assertEquals(configuration.getDatabaseUser(), "dummy");
        Assert.assertEquals(configuration.getDatabasePassword(), "dummy");
        Assert.assertEquals(configuration.getDatabaseUrl(), "jdbc:postgresql://localhost:5432/dummy");
        Assert.assertEquals(configuration.getDatabaseDriver(), "org.postgresql.Driver");
    }
}
