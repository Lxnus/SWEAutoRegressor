package de.dhbw.swe.runtime;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.database.Database;
import de.dhbw.swe.main.statistic.Statistic;
import de.dhbw.swe.main.grpc.GrpcService;
import de.dhbw.swe.runtime.inject.AutoBindingModule;
import de.dhbw.swe.runtime.ml.LinearRegression;

public class ServiceStart {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));

    Database database = injector.getInstance(Database.class);
    database.init(Statistic.class, LinearRegression.class);

    GrpcService grpcService = injector.getInstance(GrpcService.class);
    grpcService.startService();
  }
}
