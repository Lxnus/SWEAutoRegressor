package de.dhbw.swe.runtime.domain.valueObjects;

import de.dhbw.swe.main.domain.entities.SyncRepository;
import de.dhbw.swe.main.domain.valueObjects.services.LinearRegressionGrpcService;
import de.dhbw.swe.runtime.adapters.LinearRegression;
import de.dhbw.swe.runtime.inject.AutoBind;
import de.dhbw.swe.runtime.ml.regression.*;
import io.grpc.stub.StreamObserver;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.List;

@AutoBind(LinearRegressionGrpcService.class)
public class DefaultLinearRegressionGrpcService extends LinearRegressionServiceGrpc.LinearRegressionServiceImplBase implements LinearRegressionGrpcService {

  private SessionFactory sessionFactory;

  private SyncRepository<LinearRegression> regressionRepository;

  private HashMap<Long, LinearRegression> classifierMap = new HashMap<>();

  @Override
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
    this.regressionRepository = new SyncRepository<LinearRegression>(this.sessionFactory);

    List<LinearRegression> classifierList = this.regressionRepository.load(LinearRegression.class);
    classifierList.forEach(linearRegression -> classifierMap.put(linearRegression.getClassifierId(), linearRegression));
  }

  @Override
  public void setupLinearRegression(SetupLCRequest request, StreamObserver<SetupLCResponse> responseObserver) {
    long classifierId = request.getClassifierId();
    Double[] x = request.getXList().toArray(new Double[0]);
    Double[] y = request.getYList().toArray(new Double[0]);
    de.dhbw.swe.runtime.adapters.LinearRegression classifier = classifierMap.get(classifierId);
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
      regressionRepository.save(classifier);
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
      regressionRepository.delete(classifier);
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
