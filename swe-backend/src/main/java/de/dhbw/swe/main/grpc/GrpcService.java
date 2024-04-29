package de.dhbw.swe.main.grpc;

public interface GrpcService {

  void startService();

  void restartService();

  void stopService();
}
