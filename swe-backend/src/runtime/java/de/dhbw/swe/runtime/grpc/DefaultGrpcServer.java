package de.dhbw.swe.runtime.grpc;

import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import de.dhbw.swe.main.grpc.GrpcServer;
import de.dhbw.swe.runtime.inject.AutoBind;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyServerBuilder;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.inject.Inject;
import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;

@AutoBind(GrpcServer.class)
public class DefaultGrpcServer implements GrpcServer {

  private Server server;

  @Inject
  public DefaultGrpcServer(@Assisted("host") String host,
                           @Assisted("port") int port,
                           @Assisted("services") BindableService... services) {
    try {
      System.out.println("Starting grpc service-server... ");
      SslContext sslContext = loadSSLCredentials();
      SocketAddress socketAddress = new InetSocketAddress(host, port);
      ServerBuilder<NettyServerBuilder> serverBuilder = NettyServerBuilder.forAddress(socketAddress)
              .sslContext(sslContext);
      Arrays.stream(services).forEach(bindableService -> {
        System.out.print("Loading service " + bindableService.bindService().getServiceDescriptor().getName() + "... ");
        serverBuilder.addService(bindableService);
        System.out.println("done.");
      });
      server = serverBuilder.build();
      server.start();
      System.out.println("done.");
      System.out.println("Info: Grpc-Service-Server started!");
      server.awaitTermination();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  private SslContext loadSSLCredentials() throws SSLException {
    File serverCertFile = new File("properties/server-cert.pem");
    File serverKeyFile = new File("properties/server-key.pem");

    SslContextBuilder contextBuilder = SslContextBuilder.forServer(serverCertFile, serverKeyFile)
            .clientAuth(ClientAuth.NONE);
    return GrpcSslContexts.configure(contextBuilder).build();
  }

  @Override
  public void stop() {
    System.out.print("Shutdown grpc service-server... ");
    if(server != null) {
      server.shutdownNow();
      System.out.println("done.");
      return;
    }
    System.err.println("failed.");
  }
}
