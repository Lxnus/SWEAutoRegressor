package de.dhbw.swe.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.application.Database;
import de.dhbw.swe.main.domain.valueObjects.configuration.DatabaseConfiguration;
import de.dhbw.swe.runtime.application.DefaultDatabase;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import org.easymock.EasyMock;
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
    public void testDatabaseGetDriver() {
        DatabaseConfiguration database_configuration = EasyMock.createMock(DatabaseConfiguration.class);

        EasyMock.expect(database_configuration.getDatabaseDriver()).andReturn("com.mysql.cj.jdbc.Driver");

        EasyMock.replay(database_configuration);

        DefaultDatabase defaultDatabase = new DefaultDatabase(database_configuration);

        String databaseDriver = defaultDatabase.getDriver();

        Assert.assertEquals("com.mysql.cj.jdbc.Driver", databaseDriver);

        EasyMock.verify(database_configuration);
    }
}
