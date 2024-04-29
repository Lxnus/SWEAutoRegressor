package de.dhbw.swe.runtime.sampler;

import de.dhbw.swe.main.sampler.Sampler;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.runtime.inject.AutoBind;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@AutoBind(Sampler.class)
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
    public void clearCategories() {
        samplingConfigurations.clear();
    }

    @Override
    public void updateCategory(String category, SamplingConfiguration samplingConfiguration) {
        samplingConfigurations.put(category, samplingConfiguration);
    }

    @Override
    public List<Double> sample(HashMap<String, Double> samplingData, int samplingSize) {
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

        List<Double> samples = new LinkedList<>();
        for (int i = 0; i < samplingSize; i++) {
            double value = Math.abs((new Random().nextGaussian() * 150000) * avg);
            samples.add(value);
        }
        return samples;
    }
}
