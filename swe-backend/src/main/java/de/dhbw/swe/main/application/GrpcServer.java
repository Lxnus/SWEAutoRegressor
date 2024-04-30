package de.dhbw.swe.main.application;

import de.dhbw.swe.main.domain.entities.GrpcService;
import io.grpc.Server;

public interface GrpcServer {

  void start();

  void stop();

  void restart();

  Server getServer();

  void bindService(GrpcService grpcServiceRepository);

  void unbindService(GrpcService grpcServiceRepository);
}
