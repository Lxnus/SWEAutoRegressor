package de.dhbw.swe.implementation;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import de.dhbw.swe.internal.grpc.DefaultGrpcServer;
import de.dhbw.swe.main.grpc.GrpcServer;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(GrpcServer.class, DefaultGrpcServer.class)
                .build(GrpcServer.Factory.class));
    }
}