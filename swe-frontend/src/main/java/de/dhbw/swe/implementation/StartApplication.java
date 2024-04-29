package de.dhbw.swe.implementation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.dhbw.swe.main.gui.Application;

public class StartApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module());

        Application.Factory applicationFactory = injector.getInstance(Application.Factory.class);
        Application application = applicationFactory.create("SWE - Software Engineering", 800, 600);

        application.getSearchField().addSearchComponent("Kilometerstand", "km");
        application.getSearchField().addSearchComponent("Baujahr", "");
        application.getSearchField().addSearchComponent("Leistung", "PS");

        application.start();
    }
}
