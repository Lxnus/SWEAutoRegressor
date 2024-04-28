package de.dhbw.swe.main.grpc;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.grpc.DefaultGrpcClient;
import io.grpc.ManagedChannel;
import io.netty.handler.ssl.SslContext;

@ImplementedBy(DefaultGrpcClient.class)
public interface GrpcClient {

  void start(SslContext sslContext);

  ManagedChannel channel();

  void stop();

  class Factory {
    public static GrpcClient create() {
      return new DefaultGrpcClient();
    }
  }
}
