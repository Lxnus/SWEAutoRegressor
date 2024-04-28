package de.dhbw.swe.main.sampler;

import com.google.inject.assistedinject.Assisted;

public interface SamplingInterval {

    int getMinInterval();

    int getMaxInterval();

    interface Factory {
        SamplingInterval create(@Assisted("minInterval") int minInterval,
                                @Assisted("maxInterval") int maxInterval);
    }
}
