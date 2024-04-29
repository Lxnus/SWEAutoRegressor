package de.dhbw.swe.runtime.grpc;

import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.runtime.inject.AutoBind;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;

@AutoBind(GrpcClient.class)
public class DefaultGrpcClient implements GrpcClient {

  private ManagedChannel channel;

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
