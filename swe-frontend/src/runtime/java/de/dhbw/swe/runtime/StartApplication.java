package de.dhbw.swe.runtime;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.gui.Application;
import de.dhbw.swe.runtime.inject.AutoBindingModule;

public class StartApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AutoBindingModule("de.dhbw.swe"));

        Application.Factory applicationFactory = injector.getInstance(Application.Factory.class);
        Application application = applicationFactory.create("SWE - Software Engineering", 800, 600);

        application.getSearchField().addInputSearchComponent("Kilometerstand", "km", 0, 500000);
        application.getSearchField().addInputSearchComponent("Baujahr", "", 1950, 2024);
        application.getSearchField().addInputSearchComponent("Leistung", "PS", 10, 1500);

        application.start();
    }
}
