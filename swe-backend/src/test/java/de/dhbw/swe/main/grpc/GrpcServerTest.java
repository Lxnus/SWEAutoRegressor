package de.dhbw.swe.main.grpc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GrpcServerTest {

    private GrpcServer grpcServer;

    @Before
    public void setUp() {
        grpcServer = Mockito.mock(GrpcServer.class);
    }

    @Test
    public void testStop() {
        grpcServer.stop();
        Mockito.verify(grpcServer).stop();
    }
}
