package de.dhbw.swe.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.domain.valueObjects.GrpcConfiguration;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrpcConfigurationTest {

    private GrpcConfiguration configuration;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));
        configuration = injector.getInstance(GrpcConfiguration.class);
    }

    @Test
    public void testGrpcInjection() {
        Assert.assertNotNull(configuration);
    }

    @Test
    public void testGrpcPort() {
        long configurationPort = configuration.getPort();

        Assert.assertEquals(configurationPort, 8080);
    }

    @Test
    public void testGrpcHost() {
        String configurationHostname = configuration.getHostname();

        Assert.assertEquals(configurationHostname, "localhost");
    }
}
