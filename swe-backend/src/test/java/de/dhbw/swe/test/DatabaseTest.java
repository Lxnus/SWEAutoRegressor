package de.dhbw.swe.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.application.Database;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));
        database = injector.getInstance(Database.class);
    }

    @Test
    public void testDatabaseInjection() {
        Assert.assertNotNull(database);
    }

    @Test
    public void testDatabaseDriver() {
        Assert.assertEquals(database.getDriver(), "org.postgresql.Driver");
    }
}
