package de.dhbw.swe.main.grpc;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.grpc.DefaultGrpcService;

@ImplementedBy(DefaultGrpcService.class)
public interface GrpcService {

  void startService();

  void restartService();

  void stopService();
}
