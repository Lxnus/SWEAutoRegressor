package de.dhbw.swe.main.sampler;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.inject.AssistedFactory;

@AssistedFactory(SamplingConfiguration.Factory.class)
public interface SamplingConfiguration {

    SamplingInterval getInterval();

    interface Factory {
        SamplingConfiguration create(@Assisted("interval") SamplingInterval interval);
    }
}
