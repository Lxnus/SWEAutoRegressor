package de.dhbw.swe.implementation.ml;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import java.util.Arrays;

@Entity
public class LinearRegression {

  @Id
  private long classifierId;

  private int n;

  @ElementCollection
  @OrderColumn(name = "y_data")
  private Double[] y;

  @ElementCollection
  @OrderColumn(name = "x_data")
  private Double[] x;

  private double w0;
  private double w1;

  public Double[] getX() {
    return x;
  }

  public LinearRegression() {}

  public LinearRegression(long classifierId, Double[] x, Double[] y) {
    this.classifierId = classifierId;

    this.x = x;
    this.y = y;

    if (x.length != y.length) {
      throw new IllegalStateException("x and y must have the same length!");
    }
    this.n = x.length;

    // Training
    double xMean = mean(x);
    double yMean = mean(y);
    double w1Sum = 0.0D;
    for (int i = 0; i < n; i++) {
      w1Sum += x[i] * y[i];
    }
    double var1 = w1Sum - xMean * yMean * n;
    w1Sum = 0.0D;
    for (double v : x) {
      w1Sum += Math.pow(v, 2.0D);
    }
    double var2 = w1Sum - n * Math.pow(xMean, 2.0);
    this.w1 = var1 / var2;

    this.w0 = yMean - this.w1 * xMean;
  }

  public double predict(double x) {
    return g(x);
  }

  public double error() {
    return error(x, y);
  }

  private double error(Double[] x, Double[] y) {
    double sum = 0.0D;
    for (int i = 0; i < n; i++) {
      double error = error(x[i], y[i]);
      sum += error;
    }
    return (1.0D / n) * sum;
  }

  private double error(double x, double y) {
    double g = g(x);
    double delta = y - g;
    return Math.pow(delta, 2.0D);
  }

  private double mean(Double[] array) {
    double sum = 0.0D;
    for (double v : array) {
      sum += v;
    }
    return sum / array.length;
  }

  public double g(double x) {
    return w1 * x + w0;
  }

  public long getClassifierId() {
    return classifierId;
  }

  @Override
  public String toString() {
    return "LinearClassifier{" +
            "n=" + n +
            ", y=" + Arrays.toString(y) +
            ", x=" + Arrays.toString(x) +
            ", w0=" + w0 +
            ", w1=" + w1 +
            '}';
  }
}
