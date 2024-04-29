package de.dhbw.swe.runtime.inject;


import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import de.dhbw.swe.main.inject.AssistedFactory;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AutoBindingModule extends AbstractModule {
    private final String basePackage;

    public AutoBindingModule(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    protected void configure() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(basePackage))
                .setScanners(Scanners.TypesAnnotated));

        Set<Class<?>> implementations = reflections.getTypesAnnotatedWith(AutoBind.class);
        Map<Class<?>, Class<?>> implementationMap = new HashMap<>();

        for (Class<?> implClass : implementations) {
            AutoBind bindAnnotation = implClass.getAnnotation(AutoBind.class);
            Class<?> interfaceClass = bindAnnotation.value();

            implementationMap.put(interfaceClass, implClass);
            if (interfaceClass.isInterface() && interfaceClass.getAnnotation(AssistedFactory.class) == null) {
                // Bind implementation to the interface specified in the annotation
                bind((Class<Object>) interfaceClass).to(implClass);
            }
        }

        Set<Class<?>> targetClasses = reflections.getTypesAnnotatedWith(AssistedFactory.class, true);
        for (Class<?> targetClass : targetClasses) {
            AssistedFactory assistedFactory = targetClass.getDeclaredAnnotation(AssistedFactory.class);
            Class<?> factoryClass = assistedFactory.value();
            install(new FactoryModuleBuilder()
                    .implement((Class<Object>)targetClass, implementationMap.get(targetClass))
                    .build(factoryClass));
        }
    }
}