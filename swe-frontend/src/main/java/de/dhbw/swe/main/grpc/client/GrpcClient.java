package de.dhbw.swe.main.grpc.client;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.grpc.client.GrpcClientImpl;
import io.grpc.ManagedChannel;
import io.netty.handler.ssl.SslContext;

@ImplementedBy(GrpcClientImpl.class)
public interface GrpcClient {

  void start(SslContext sslContext);

  ManagedChannel channel();

  void stop();

  class Factory {
    public static GrpcClient create() {
      return new GrpcClientImpl();
    }
  }
}
