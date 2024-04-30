package de.dhbw.swe.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.application.Database;
import de.dhbw.swe.main.domain.valueObjects.services.LinearRegressionGrpcService;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrpcServiceTest {

    private LinearRegressionGrpcService service;

    private Database database;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));
        service = injector.getInstance(LinearRegressionGrpcService.class);

        database = injector.getInstance(Database.class);
        service.setSessionFactory(database.getSession());
    }

    @Test
    public void testInjection() {
        Assert.assertNotNull(service);
    }

    @Test
    public void testSession() {
        Assert.assertNotNull(service.getSessionFactory());
        Assert.assertEquals(service.getSessionFactory(), database.getSession());
    }
}
