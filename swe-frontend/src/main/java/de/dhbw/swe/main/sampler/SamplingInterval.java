package de.dhbw.swe.main.sampler;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.inject.AssistedFactory;

@AssistedFactory(SamplingInterval.Factory.class)
public interface SamplingInterval {

    int getMinInterval();

    int getMaxInterval();

    interface Factory {
        SamplingInterval create(@Assisted("minInterval") int minInterval,
                                @Assisted("maxInterval") int maxInterval);
    }
}
