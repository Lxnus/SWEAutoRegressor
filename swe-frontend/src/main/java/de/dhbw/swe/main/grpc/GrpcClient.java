package de.dhbw.swe.main.grpc;

import io.grpc.ManagedChannel;
import io.netty.handler.ssl.SslContext;

public interface GrpcClient {

  void start(SslContext sslContext);

  ManagedChannel channel();

  void stop();
}
