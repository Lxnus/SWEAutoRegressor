package de.dhbw.swe.test;

import de.dhbw.swe.runtime.adapters.LinearRegression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinearRegressionTest {

    private LinearRegression linearRegression;

    @Before
    public void setUp() {
        long classifierId = 1;
        Double[] x = {1.0, 2.0, 3.0, 4.0, 5.0};
        Double[] y = {1.0, 2.0, 3.0, 4.0, 5.0};

        linearRegression = new LinearRegression(classifierId, x, y);
    }

    @Test
    public void testLinearRegression() {
        double regressionError = linearRegression.error();
        double expectedError = 0.0;
        Assert.assertEquals(expectedError, regressionError, 0.0);
    }
}
