package de.dhbw.swe.main.sampler;

import java.util.HashMap;
import java.util.List;

public interface Sampler {

    void addCategory(String category, SamplingConfiguration samplingConfiguration);

    SamplingConfiguration getSamplingConfiguration(String category);

    void removeCategory(String category);

    void clearCategories();

    void updateCategory(String category, SamplingConfiguration samplingConfiguration);

    List<Double> sample(HashMap<String, Double> samplingData, int samplingSize);
}
