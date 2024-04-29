package de.dhbw.swe.main.grpc;

import io.netty.handler.ssl.SslContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class GrcpClientTest {

    private GrpcClient grpcClient;
    private SslContext sslContext;

    @Before
    public void setUp() {
        grpcClient = Mockito.mock(GrpcClient.class);
        sslContext = Mockito.mock(SslContext.class);
    }

    @Test
    public void testStart() {
        grpcClient.start(sslContext);
        Mockito.verify(grpcClient).start(sslContext);
    }

    @Test
    public void testChannel() {
        grpcClient.channel();
        Mockito.verify(grpcClient).channel();
    }

    @Test
    public void testStop() {
        grpcClient.stop();
        Mockito.verify(grpcClient).stop();
    }
}
