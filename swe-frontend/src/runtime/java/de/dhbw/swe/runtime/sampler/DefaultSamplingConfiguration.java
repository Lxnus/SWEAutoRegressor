package de.dhbw.swe.runtime.sampler;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.main.sampler.SamplingInterval;
import de.dhbw.swe.runtime.inject.AutoBind;

@AutoBind(SamplingConfiguration.class)
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
