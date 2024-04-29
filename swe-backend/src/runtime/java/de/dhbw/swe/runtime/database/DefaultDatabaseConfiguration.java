package de.dhbw.swe.runtime.database;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dhbw.swe.main.database.DatabaseConfiguration;
import de.dhbw.swe.runtime.inject.AutoBind;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Singleton
@AutoBind(DatabaseConfiguration.class)
public class DefaultDatabaseConfiguration implements DatabaseConfiguration {

    private final Properties properties;

    @Inject
    public DefaultDatabaseConfiguration(Properties properties) {
        this.properties = properties;
        try {
            String path = new File("properties/database.properties").getAbsolutePath();
            this.properties.load(new FileInputStream(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String getDatabaseUser() {
        return this.properties.getProperty("database.user");
    }

    @Override
    public String getDatabasePassword() {
        return this.properties.getProperty("database.password");
    }

    @Override
    public String getDatabaseUrl() {
        return this.properties.getProperty("database.url");
    }

    @Override
    public String getDatabaseDriver() {
        return this.properties.getProperty("database.driver");
    }
}
