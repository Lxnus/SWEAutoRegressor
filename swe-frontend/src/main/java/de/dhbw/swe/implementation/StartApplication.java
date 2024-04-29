package de.dhbw.swe.implementation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.gui.Application;

public class StartApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module());

        Application.Factory applicationFactory = injector.getInstance(Application.Factory.class);
        Application application = applicationFactory.create("SWE - Software Engineering", 800, 600);

        application.getSearchField().addSearchComponent("Kilometerstand", "km", 0, 500000);
        application.getSearchField().addSearchComponent("Baujahr", "", 1950, 2024);
        application.getSearchField().addSearchComponent("Leistung", "PS", 10, 1500);

        application.start();
    }
}
