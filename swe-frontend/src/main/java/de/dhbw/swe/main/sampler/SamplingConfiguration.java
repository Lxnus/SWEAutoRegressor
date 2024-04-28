package de.dhbw.swe.main.sampler;

import com.google.inject.assistedinject.Assisted;

public interface SamplingConfiguration {

    SamplingInterval getInterval();

    interface Factory {
        SamplingConfiguration create(@Assisted("interval") SamplingInterval interval);
    }
}
