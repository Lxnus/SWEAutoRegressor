package de.dhbw.swe.runtime.ml;

import de.dhbw.swe.implementation.database.SyncRepository;
import de.dhbw.swe.implementation.database.entities.Statistic;
import de.dhbw.swe.implementation.ml.LinearRegression;
import de.dhbw.swe.main.database.Database;
import de.dhbw.swe.main.statistic.StatisticService;
import de.dhbw.swe.runtime.ml.regression.*;
import io.grpc.stub.StreamObserver;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

public class LinearRegressionService extends LinearRegressionServiceGrpc.LinearRegressionServiceImplBase {

  private final StatisticService statisticService;
  private final SyncRepository<LinearRegression> repository;
  private final HashMap<Long, LinearRegression> classifierMap = new HashMap<>();

  @Inject
  public LinearRegressionService(Database database,
                                 StatisticService statisticService) {
    this.statisticService = statisticService;
    repository = database.createRepository(LinearRegression.class);

    System.out.print("Loading linear classifiers... ");
    List<LinearRegression> classifierList = repository.load(LinearRegression.class);
    System.out.println("done.");
    System.out.println("Setup [" + classifierList.size() + "] linear classifiers... ");
    classifierList.forEach(LinearRegression -> classifierMap.put(LinearRegression.getClassifierId(), LinearRegression));
    System.out.println("done.");
  }

  @Override
  public void setupLinearRegression(SetupLCRequest request, StreamObserver<SetupLCResponse> responseObserver) {
    long classifierId = request.getClassifierId();
    Double[] x = request.getXList().toArray(new Double[0]);
    Double[] y = request.getYList().toArray(new Double[0]);
    LinearRegression classifier = classifierMap.get(classifierId);
    if(classifier == null) {
      classifier = new LinearRegression(classifierId, x, y);
      classifierMap.put(classifierId, classifier);
      SetupLCResponse response = SetupLCResponse.newBuilder()
              .setClassifierId(classifierId)
              .setMessage("Ok")
              .setSuccess(true)
              .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
      repository.save(classifier);
      Statistic statistic = statisticService.getCurrentStatistic();
      statistic.adjustLinearClassifierOnline(1);
      statistic.adjustLinearClassifierCreated(1);
      statisticService.update(statistic);
      System.out.println("Classifier created. ClassifierId: " + classifierId);
      return;
    }
    SetupLCResponse response = SetupLCResponse.newBuilder()
            .setClassifierId(classifierId)
            .setMessage("Classifier already exist. Delete the classifier or use another classifierId.")
            .setSuccess(false)
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void deleteLinearRegression(DeleteLCRequest request, StreamObserver<DeleteLCResponse> responseObserver) {
    long classifierId = request.getClassifierId();
    LinearRegression classifier = classifierMap.get(classifierId);
    if(classifier != null) {
      classifierMap.remove(classifierId);
      DeleteLCResponse response = DeleteLCResponse.newBuilder()
              .setClassifierId(classifierId)
              .setMessage("Ok")
              .setSuccess(true)
              .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
      repository.delete(classifier);
      Statistic statistic = statisticService.getCurrentStatistic();
      statistic.adjustLinearClassifierOnline(-1);
      statisticService.update(statistic);
      System.out.println("Classifier deleted. ClassifierId: " + classifierId);
      return;
    }
    DeleteLCResponse response = DeleteLCResponse.newBuilder()
            .setClassifierId(classifierId)
            .setMessage("Cannot delete classifier. Maybe the classier does not exist!")
            .setSuccess(false)
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void errorLinearRegression(ErrorLCRequest request, StreamObserver<ErrorLCResponse> responseObserver) {
    long classifierId = request.getClassifierId();
    LinearRegression classifier = classifierMap.get(classifierId);
    if(classifier != null) {
      double error = classifier.error();
      ErrorLCResponse response = ErrorLCResponse.newBuilder()
              .setClassifierId(classifierId)
              .setError(error)
              .setMessage("Ok")
              .setSuccess(true)
              .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
      return;
    }
    ErrorLCResponse response = ErrorLCResponse.newBuilder()
            .setClassifierId(classifierId)
            .setMessage("Cannot get error of classifier. Maybe the classier does not exist!")
            .setSuccess(false)
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void predictLinearRegression(PredictLCRequest request, StreamObserver<PredictLCResponse> responseObserver) {
    long classifierId = request.getClassifierId();
    double value = request.getValue();
    LinearRegression classifier = classifierMap.get(classifierId);
    if(classifier != null) {
      double prediction = classifier.predict(value);
      PredictLCResponse response = PredictLCResponse.newBuilder()
              .setClassifierId(classifierId)
              .setPrediction(prediction)
              .setValue(value)
              .setMessage("Ok")
              .setSuccess(true)
              .build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
      return;
    }
    PredictLCResponse response = PredictLCResponse.newBuilder()
            .setClassifierId(classifierId)
            .setValue(value)
            .setMessage("Cannot get prediction of classifier. Maybe the classier does not exist!")
            .setSuccess(false)
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
