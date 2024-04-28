package de.dhbw.swe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.implementation.Module;
import de.dhbw.swe.main.sampler.Sampler;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.main.sampler.SamplingInterval;

import java.util.HashMap;

public class TestSampling {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module());

        HashMap<String, Double> samplingData = new HashMap<>();
        samplingData.put("test", 100.0);

        Sampler sampler = injector.getInstance(Sampler.class);

        SamplingInterval.Factory intervalFactory = injector.getInstance(SamplingInterval.Factory.class);
        SamplingInterval interval = intervalFactory.create(0, 10000);

        SamplingConfiguration.Factory sampFactory = injector.getInstance(SamplingConfiguration.Factory.class);
        SamplingConfiguration sampConfig = sampFactory.create(interval);

        sampler.addCategory("test", sampConfig);
        double[] sample = sampler.sample(samplingData, 10);
        for (double value : sample) {
            System.out.println(value);
        }
    }
}
