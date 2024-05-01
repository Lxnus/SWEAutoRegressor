package de.dhbw.swe.runtime;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.application.Database;
import de.dhbw.swe.main.application.GrpcServer;
import de.dhbw.swe.main.domain.services.LinearRegressionGrpcService;
import de.dhbw.swe.runtime.adapters.LinearRegression;
import de.dhbw.swe.runtime.inject.AutoBindingModule;

public class ServiceStart {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));

    Database database = injector.getInstance(Database.class);
    database.init(LinearRegression.class);

    GrpcServer grpcServer = injector.getInstance(GrpcServer.class);

    // Bind services to server
    grpcServer.bindService(injector.getInstance(LinearRegressionGrpcService.class));

    grpcServer.start();
  }
}
