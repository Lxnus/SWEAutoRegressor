package de.dhbw.swe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.implementation.Module;
import de.dhbw.swe.implementation.database.entities.Statistic;
import de.dhbw.swe.implementation.ml.LinearRegression;
import de.dhbw.swe.main.database.Database;
import de.dhbw.swe.main.grpc.GrpcService;

public class ServiceStart {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new Module());

    Database database = injector.getInstance(Database.class);
    database.init(Statistic.class, LinearRegression.class);

    GrpcService grpcService = injector.getInstance(GrpcService.class);
    grpcService.startService();
  }
}
