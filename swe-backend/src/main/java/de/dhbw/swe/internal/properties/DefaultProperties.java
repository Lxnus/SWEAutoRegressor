package de.dhbw.swe.internal.properties;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dhbw.swe.main.properties.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Singleton
public class DefaultProperties implements Properties {

  private final java.util.Properties properties;

  @Inject
  public DefaultProperties(java.util.Properties properties) {
    this.properties = properties;
    try {
      String path = new File("properties/alphos.properties").getAbsolutePath();
      properties.load(new FileInputStream(path));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
