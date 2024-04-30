package de.dhbw.swe.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.application.GrpcServer;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrpcServerTest {

    private GrpcServer grpcServer;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));
        grpcServer = injector.getInstance(GrpcServer.class);
    }

    @Test
    public void testGrpcServerInjection() {
        Assert.assertNotNull(grpcServer);
    }
}
