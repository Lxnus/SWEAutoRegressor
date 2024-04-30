package de.dhbw.swe.main.application;

import de.dhbw.swe.main.domain.entities.GrpcService;

public interface GrpcServer {

  void start();

  void stop();

  void restart();

  void bindService(GrpcService grpcServiceRepository);

  void unbindService(GrpcService grpcServiceRepository);
}
