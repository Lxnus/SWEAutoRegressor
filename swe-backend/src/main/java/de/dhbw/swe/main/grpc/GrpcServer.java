package de.dhbw.swe.main.grpc;

import com.google.inject.ImplementedBy;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.internal.grpc.DefaultGrpcServer;
import io.grpc.BindableService;

@ImplementedBy(DefaultGrpcServer.class)
public interface GrpcServer {

  void stop();

  interface Factory {
    GrpcServer create(@Assisted("host") String host,
                      @Assisted("port") int port,
                      @Assisted("services") BindableService... services);
  }
}
