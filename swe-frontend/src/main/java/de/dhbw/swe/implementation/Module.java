package de.dhbw.swe.implementation;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import de.dhbw.swe.internal.grpc.DefaultGrpcClient;
import de.dhbw.swe.internal.grpc.services.DefaultLinearRegressionService;
import de.dhbw.swe.internal.gui.DefaultApplication;
import de.dhbw.swe.internal.gui.DefaultComponent;
import de.dhbw.swe.internal.gui.DefaultSearchField;
import de.dhbw.swe.internal.sampler.DefaultSamplingConfiguration;
import de.dhbw.swe.internal.sampler.DefaultSamplingInterval;
import de.dhbw.swe.main.grpc.GrpcClient;
import de.dhbw.swe.main.grpc.services.LinearRegressionService;
import de.dhbw.swe.main.gui.Application;
import de.dhbw.swe.main.gui.Component;
import de.dhbw.swe.main.gui.SearchField;
import de.dhbw.swe.main.sampler.SamplingConfiguration;
import de.dhbw.swe.main.sampler.SamplingInterval;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        install(
                new FactoryModuleBuilder()
                        .implement(SamplingInterval.class, DefaultSamplingInterval.class)
                        .build(SamplingInterval.Factory.class)
        );
        install(
                new FactoryModuleBuilder()
                        .implement(SamplingConfiguration.class, DefaultSamplingConfiguration.class)
                        .build(SamplingConfiguration.Factory.class)
        );
        install(
                new FactoryModuleBuilder()
                        .implement(Component.class, DefaultComponent.class)
                        .build(Component.Factory.class)
        );
        install(
                new FactoryModuleBuilder()
                        .implement(SearchField.class, DefaultSearchField.class)
                        .build(SearchField.Factory.class)
        );
        install(
                new FactoryModuleBuilder()
                        .implement(Application.class, DefaultApplication.class)
                        .build(Application.Factory.class)
        );
    }
}
