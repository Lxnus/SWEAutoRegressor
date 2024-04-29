package de.dhbw.swe.main.sampler;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.sampler.DefaultSampler;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@ImplementedBy(DefaultSampler.class)
public interface Sampler {

    void addCategory(String category, SamplingConfiguration samplingConfiguration);

    SamplingConfiguration getSamplingConfiguration(String category);

    void removeCategory(String category);

    void clearCategories();

    void updateCategory(String category, SamplingConfiguration samplingConfiguration);

    List<Double> sample(HashMap<String, Double> samplingData, int samplingSize);
}
