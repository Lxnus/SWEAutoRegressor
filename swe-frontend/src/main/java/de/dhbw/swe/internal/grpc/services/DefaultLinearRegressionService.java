package de.dhbw.swe.internal.grpc.services;

import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.main.grpc.services.LinearRegressionService;
import net.alphos.service.grpc.services.classifier.*;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultLinearRegressionService implements LinearRegressionService {

  private static final Logger logger = Logger.getLogger(DefaultLinearRegressionService.class.getName());

  private final LinearRegressionServiceGrpc.LinearRegressionServiceBlockingStub blockingStub;

  @Inject
  public DefaultLinearRegressionService(GrpcClient client) {
    this.blockingStub = LinearRegressionServiceGrpc.newBlockingStub(client.channel());
  }

  @Override
  public void create(long classifierId, List<Double> X, List<Double> Y) {
    SetupLCRequest request = SetupLCRequest.newBuilder()
            .setClassifierId(classifierId)
            .addAllX(X)
            .addAllY(Y)
            .build();
    SetupLCResponse response;
    try {
      response = blockingStub.setupLinearRegression(request);
    } catch (Exception exception) {
      logger.log(Level.SEVERE, "Request failed: " + exception.getMessage());
      exception.printStackTrace();
      return;
    }
    if(!response.getSuccess()) {
      logger.log(Level.SEVERE, response.getMessage());
      return;
    }
    logger.info("Linear classifier created. Status: " + response.getMessage());
  }

  @Override
  public double error(long classifierId) {
    ErrorLCRequest request = ErrorLCRequest.newBuilder()
            .setClassifierId(classifierId)
            .build();

    ErrorLCResponse response;
    try {
      response = blockingStub.errorLinearRegression(request);
    } catch (Exception exception) {
      logger.log(Level.SEVERE, "Request failed: " + exception.getMessage());
      return Double.NaN;
    }
    if(!response.getSuccess()) {
      logger.log(Level.SEVERE, response.getMessage());
      return Double.NaN;
    }
    return response.getError();
  }

  @Override
  public double predict(double x, long classifierId) {
    PredictLCRequest request = PredictLCRequest.newBuilder()
            .setClassifierId(classifierId)
            .setValue(x)
            .build();

    PredictLCResponse response;
    try {
      response = blockingStub.predictLinearRegression(request);
    } catch (Exception exception) {
      logger.log(Level.SEVERE, "Request failed: " + exception.getMessage());
      return Double.NaN;
    }
    if(!response.getSuccess()) {
      logger.log(Level.SEVERE, response.getMessage());
      return Double.NaN;
    }
    return response.getPrediction();
  }

  @Override
  public void delete(long classifierId) {
    DeleteLCRequest request = DeleteLCRequest.newBuilder()
            .setClassifierId(classifierId)
            .build();

    DeleteLCResponse response;
    try {
      response = blockingStub.deleteLinearRegression(request);
    } catch (Exception exception) {
      logger.log(Level.SEVERE, "Request failed: " + exception.getMessage());
      return;
    }
    if(!response.getSuccess()) {
      logger.log(Level.SEVERE, response.getMessage());
      return;
    }
    logger.info("Linear classifier deleted. Status: " + response.getMessage());
  }
}
