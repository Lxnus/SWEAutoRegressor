package de.dhbw.swe.main.properties;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.internal.properties.DefaultProperties;

@ImplementedBy(DefaultProperties.class)
public interface Properties {

  String getProperty(String key);
}
