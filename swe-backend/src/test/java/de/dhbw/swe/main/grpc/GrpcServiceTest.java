package de.dhbw.swe.main.grpc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GrpcServiceTest {

    private GrpcService grpcService;

    @Before
    public void setUp() {
        grpcService = Mockito.mock(GrpcService.class);
    }

    @Test
    public void testStartService() {
        grpcService.startService();
        Mockito.verify(grpcService).startService();
    }

    @Test
    public void testRestartService() {
        grpcService.restartService();
        Mockito.verify(grpcService).restartService();
    }

    @Test
    public void testStopService() {
        grpcService.stopService();
        Mockito.verify(grpcService).stopService();
    }
}
