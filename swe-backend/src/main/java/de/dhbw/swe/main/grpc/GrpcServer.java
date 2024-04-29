package de.dhbw.swe.main.grpc;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.inject.AssistedFactory;
import io.grpc.BindableService;

@AssistedFactory(GrpcServer.Factory.class)
public interface GrpcServer {

  void stop();

  interface Factory {
    GrpcServer create(@Assisted("host") String host,
                      @Assisted("port") int port,
                      @Assisted("services") BindableService... services);
  }
}
