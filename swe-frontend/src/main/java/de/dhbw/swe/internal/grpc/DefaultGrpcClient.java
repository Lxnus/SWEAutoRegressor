package de.dhbw.swe.internal.grpc;

import de.dhbw.swe.main.grpc.GrpcClient;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;

import javax.inject.Inject;


public class DefaultGrpcClient implements GrpcClient {

  private ManagedChannel channel;

  @Inject
  public DefaultGrpcClient() {}

  @Override
  public void start(SslContext sslContext) {
    if(sslContext == null) {
      throw new NullPointerException();
    }
    channel = NettyChannelBuilder.forAddress("127.0.0.1", 222)
            .sslContext(sslContext)
            .build();
  }

  @Override
  public ManagedChannel channel() {
    return channel;
  }

  @Override
  public void stop() {
    if(channel != null) {
      channel.shutdownNow();
    }
  }
}
