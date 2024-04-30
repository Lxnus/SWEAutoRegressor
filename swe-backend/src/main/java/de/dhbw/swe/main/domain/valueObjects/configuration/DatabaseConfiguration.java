package de.dhbw.swe.main.domain.valueObjects.configuration;

import de.dhbw.swe.main.domain.entities.Configuration;

public interface DatabaseConfiguration extends Configuration {

    String getDatabaseUser();

    String getDatabasePassword();

    String getDatabaseUrl();

    String getDatabaseDriver();
}
