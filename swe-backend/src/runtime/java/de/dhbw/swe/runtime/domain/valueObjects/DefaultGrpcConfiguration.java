package de.dhbw.swe.runtime.domain.valueObjects;

import com.google.inject.Inject;
import de.dhbw.swe.main.domain.valueObjects.configuration.GrpcConfiguration;
import de.dhbw.swe.runtime.inject.AutoBind;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@AutoBind(GrpcConfiguration.class)
public class DefaultGrpcConfiguration implements GrpcConfiguration {

    private final Properties properties;

    @Inject
    public DefaultGrpcConfiguration(Properties properties) {
        this.properties = properties;
        try {
            String path = new File("properties/grpc.properties").getAbsolutePath();
            this.properties.load(new FileInputStream(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String getHostname() {
        return this.properties.getProperty("grpc.host");
    }

    @Override
    public int getPort() {
        return Integer.parseInt(this.properties.getProperty("grpc.port"));
    }

    @Override
    public String getProperty(String propertyKey) {
        return this.properties.getProperty(propertyKey);
    }
}
