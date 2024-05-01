package de.dhbw.swe.main.domain.valueObjects;

import de.dhbw.swe.main.domain.entities.Configuration;

public interface GrpcConfiguration extends Configuration {

    String getHostname();

    int getPort();
}
