package de.dhbw.swe.internal.sampler;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.main.sampler.SamplingInterval;

import javax.inject.Inject;

public class DefaultSamplingConfiguration implements SamplingConfiguration {

    private final SamplingInterval interval;

    @Inject
    public DefaultSamplingConfiguration(@Assisted("interval") SamplingInterval interval) {
        this.interval = interval;
    }

    @Override
    public SamplingInterval getInterval() {
        return this.interval;
    }
}
