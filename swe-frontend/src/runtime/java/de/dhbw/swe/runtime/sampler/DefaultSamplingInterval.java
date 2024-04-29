package de.dhbw.swe.internal.sampler;

import com.google.inject.assistedinject.Assisted;
import de.dhbw.swe.main.sampler.SamplingInterval;
import de.dhbw.swe.runtime.inject.AutoBind;

import javax.inject.Inject;

@AutoBind(SamplingInterval.class)
public class DefaultSamplingInterval implements SamplingInterval {

    private final int minInterval;
    private final int maxInterval;

    @Inject
    public DefaultSamplingInterval(@Assisted("minInterval") int minInterval,
                                   @Assisted("maxInterval") int maxInterval) {
        this.minInterval = minInterval;
        this.maxInterval = maxInterval;
    }

    @Override
    public int getMinInterval() {
        return minInterval;
    }

    @Override
    public int getMaxInterval() {
        return maxInterval;
    }
}
