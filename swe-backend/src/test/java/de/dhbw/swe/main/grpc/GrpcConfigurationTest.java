package de.dhbw.swe.main.grpc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GrpcConfigurationTest {

    private GrpcConfiguration grpcConfiguration;

    @Before
    public void setUp() {
        grpcConfiguration = Mockito.mock(GrpcConfiguration.class);
    }

    @Test
    public void testGetHostname() {
        grpcConfiguration.getHostname();
        Mockito.verify(grpcConfiguration).getHostname();
    }

    @Test
    public void testGetPort() {
        grpcConfiguration.getPort();
        Mockito.verify(grpcConfiguration).getPort();
    }
}
