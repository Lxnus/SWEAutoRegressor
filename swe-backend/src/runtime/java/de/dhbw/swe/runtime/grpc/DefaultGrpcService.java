package de.dhbw.swe.runtime.grpc;

import de.dhbw.swe.implementation.grpc.services.LinearRegressionService;
import de.dhbw.swe.main.grpc.GrpcServer;
import de.dhbw.swe.main.grpc.GrpcService;
import de.dhbw.swe.runtime.inject.AutoBind;

import javax.inject.Inject;

@AutoBind(GrpcService.class)
public class DefaultGrpcService implements GrpcService {

  private final GrpcServer.Factory factory;
  private final GrpcConfiguration grpcConfiguration;

  //Services
  private final LinearRegressionService linearRegressionService;

  private GrpcServer server;

  @Inject
  public DefaultGrpcService(GrpcServer.Factory factory,
                            GrpcConfiguration grpcConfiguration,
                            LinearRegressionService linearRegressionService) {
    this.grpcConfiguration = grpcConfiguration;
    this.factory = factory;

    this.linearRegressionService = linearRegressionService;
  }

  @Override
  public void startService() {
    String host = grpcConfiguration.getHostname();
    int port = grpcConfiguration.getPort();
    server = factory.create(host, port, linearRegressionService);
  }

  @Override
  public void restartService() {
    System.out.println("Restarting service...");
    stopService();
    startService();
  }

  @Override
  public void stopService() {
    if(server != null) {
      server.stop();
    }
  }
}
