package de.dhbw.swe.main.sampler;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.sampler.DefaultSampler;

import java.util.HashMap;
import java.util.LinkedList;

@ImplementedBy(DefaultSampler.class)
public interface Sampler {

    void addCategory(String category, SamplingConfiguration samplingConfiguration);

    SamplingConfiguration getSamplingConfiguration(String category);

    void removeCategory(String category);

    void updateCategory(String category, SamplingConfiguration samplingConfiguration);

    double[] sample(HashMap<String, Double> samplingData, int samplingSize);
}
