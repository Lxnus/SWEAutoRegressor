package de.dhbw.swe.internal.sampler;

import de.dhbw.swe.main.sampler.Sampler;
import de.dhbw.swe.main.sampler.SamplingConfiguration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class DefaultSampler implements Sampler {

    private final HashMap<String, SamplingConfiguration> samplingConfigurations;

    public DefaultSampler() {
        samplingConfigurations = new HashMap<>();
    }

    @Override
    public void addCategory(String category, SamplingConfiguration samplingConfiguration) {
        samplingConfigurations.put(category, samplingConfiguration);
    }

    @Override
    public SamplingConfiguration getSamplingConfiguration(String category) {
        return samplingConfigurations.get(category);
    }

    @Override
    public void removeCategory(String category) {
        samplingConfigurations.remove(category);
    }

    @Override
    public void updateCategory(String category, SamplingConfiguration samplingConfiguration) {
        samplingConfigurations.put(category, samplingConfiguration);
    }

    @Override
    public double[] sample(HashMap<String, Double> samplingData, int samplingSize) {
        LinkedList<Double> scores = new LinkedList<>();
        for (String category : samplingData.keySet()) {
            if (!samplingConfigurations.containsKey(category)) {
                throw new IllegalArgumentException("Category " + category + " not found in sampling configurations");
            }
            double value = samplingData.get(category);

            double intervalCenter = this.samplingConfigurations.get(category).getInterval().getMinInterval() + this.samplingConfigurations.get(category).getInterval().getMaxInterval();
            intervalCenter /= 2;

            double distance = Math.sqrt(Math.pow(value - intervalCenter, 2));
            double score = 1.0 - (distance / Math.sqrt(Math.pow(this.samplingConfigurations.get(category).getInterval().getMaxInterval() - intervalCenter, 2)));
            scores.add(score);
        }
        double avg = scores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        double[] samples = new double[samplingSize];
        for (int i = 0; i < samplingSize; i++) {
            samples[i] = Math.abs((new Random().nextGaussian() * 100000) * avg);
        }
        return samples;
    }
}
