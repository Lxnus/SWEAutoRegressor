package de.dhbw.swe.test;

import de.dhbw.swe.runtime.adapters.LinearRegression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinearRegressionTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testLinearRegressionPredict() {
        long classifierId = 1;
        Double[] x = {1.0, 2.0, 3.0, 4.0, 5.0};
        Double[] y = {1.0, 2.0, 3.0, 4.0, 5.0};
        LinearRegression linearRegression = new LinearRegression(classifierId, x, y);

        double prediction = linearRegression.predict(6.0);

        double expectedPrediction = 6.0;
        Assert.assertEquals(expectedPrediction, prediction, 1E-6);
    }

    @Test
    public void testLinearRegressionError() {
        long classifierId = 1;
        Double[] x = {1.0, 2.0, 3.0, 4.0, 5.0};
        Double[] y = {1.0, 2.0, 3.0, 4.0, 5.0};

        LinearRegression linearRegression = new LinearRegression(classifierId, x, y);

        double regressionError = linearRegression.error();

        double expectedError = 0.0;
        Assert.assertEquals(expectedError, regressionError, 1E-6);
    }
}
