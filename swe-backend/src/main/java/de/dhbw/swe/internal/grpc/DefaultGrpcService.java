package de.dhbw.swe.internal.grpc;

import de.dhbw.swe.implementation.grpc.services.LinearRegressionService;
import de.dhbw.swe.main.grpc.GrpcServer;
import de.dhbw.swe.main.grpc.GrpcService;
import de.dhbw.swe.main.properties.Properties;

import javax.inject.Inject;

public class DefaultGrpcService implements GrpcService {

  private final Properties properties;
  private final GrpcServer.Factory factory;

  //Services
  private final LinearRegressionService linearRegressionService;

  private GrpcServer server;

  @Inject
  public DefaultGrpcService(Properties properties,
                            GrpcServer.Factory factory,
                            LinearRegressionService linearRegressionService) {
    this.properties = properties;
    this.factory = factory;

    this.linearRegressionService = linearRegressionService;
  }

  @Override
  public void startService() {
    String host = properties.getProperty("alphos-service-server-host");
    int port = Integer.parseInt(properties.getProperty("alphos-service-server-port"));
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
