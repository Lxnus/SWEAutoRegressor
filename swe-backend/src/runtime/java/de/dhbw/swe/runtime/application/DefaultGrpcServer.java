package de.dhbw.swe.runtime.application;

import com.google.inject.Singleton;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.application.Database;
import de.dhbw.swe.main.application.GrpcServer;
import de.dhbw.swe.main.domain.entities.GrpcService;
import de.dhbw.swe.main.domain.valueObjects.configuration.GrpcConfiguration;
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
import java.util.LinkedList;

@Singleton
@AutoBind(GrpcServer.class)
public class DefaultGrpcServer implements GrpcServer {

  private final String hostname;
  private final int port;

  private Server server;

  private final LinkedList<GrpcService> grpcGrpcServiceRepository;

  @Inject
  public DefaultGrpcServer(GrpcConfiguration grpcConfiguration) {
    this.hostname = grpcConfiguration.getHostname();
    this.port = grpcConfiguration.getPort();

    this.grpcGrpcServiceRepository = new LinkedList<>();
  }

  private SslContext loadSSLCredentials() throws SSLException {
    File serverCertFile = new File("properties/server-cert.pem");
    File serverKeyFile = new File("properties/server-key.pem");

    SslContextBuilder contextBuilder = SslContextBuilder
            .forServer(serverCertFile, serverKeyFile)
            .clientAuth(ClientAuth.NONE);
    return GrpcSslContexts.configure(contextBuilder).build();
  }

  @Override
  public void start() {
    try {
      System.out.println("Starting Grpc-Modules... ");

      SslContext sslContext = loadSSLCredentials();
      SocketAddress socketAddress = new InetSocketAddress(this.hostname, this.port);
      ServerBuilder<NettyServerBuilder> serverBuilder = NettyServerBuilder
              .forAddress(socketAddress)
              .sslContext(sslContext);

      this.grpcGrpcServiceRepository.forEach(bindableGrpcService -> serverBuilder.addService((BindableService) bindableGrpcService));
      this.server = serverBuilder.build();
      this.server.start();

      System.out.println("Info: Grpc-Server started!");
      this.server.awaitTermination();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void restart() {
    System.out.print("Restarting grpc service-server... ");
    this.stop();
    this.start();
    System.err.println("failed.");
  }

  @Override
  public void stop() {
    System.out.print("Shutdown grpc service-server... ");
    if(this.server != null) {
      this.server.shutdownNow();
      System.out.println("done.");
      return;
    }
    System.err.println("failed.");
  }

  @Override
  public void bindService(GrpcService grpcServiceRepository) {
    this.grpcGrpcServiceRepository.add(grpcServiceRepository);
  }

  @Override
  public void unbindService(GrpcService grpcServiceRepository) {
    this.grpcGrpcServiceRepository.remove(grpcServiceRepository);
  }
}
