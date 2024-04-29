package de.dhbw.swe.main.database;

public interface DatabaseConfiguration {

    String getDatabaseUser();

    String getDatabasePassword();

    String getDatabaseUrl();

    String getDatabaseDriver();
}
