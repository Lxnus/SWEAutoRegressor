package de.dhbw.swe.internal.database;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dhbw.swe.implementation.database.SyncRepository;
import de.dhbw.swe.main.database.Database;
import de.dhbw.swe.main.properties.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class DefaultDatabase implements Database {

  private final Properties properties;

  private SessionFactory sessionFactory;

  @Inject
  public DefaultDatabase(Properties properties) {
    this.properties = properties;
  }

  @Override
  public void init(Class<?>... classes) {
    StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

    Map<String, Object> settings = new HashMap<>();
    settings.put(Environment.DRIVER, properties.getProperty("alphos-database-driver"));
    settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");
    settings.put(Environment.URL, properties.getProperty("alphos-database-url"));
    settings.put(Environment.USER, properties.getProperty("alphos-database-username"));
    settings.put(Environment.PASS, properties.getProperty("alphos-database-password"));
    settings.put(Environment.HBM2DDL_AUTO, "update");
    settings.put(Environment.SHOW_SQL, false);
    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
    settings.put(Environment.ORDER_INSERTS, true);
    settings.put(Environment.ORDER_UPDATES, true);

    registryBuilder.applySettings(settings);

    StandardServiceRegistry registry = registryBuilder.build();
    MetadataSources metaDataSources = new MetadataSources(registry);

    Arrays.stream(classes).forEach(metaDataSources::addAnnotatedClass);

    Metadata metaData = metaDataSources.getMetadataBuilder().build();

    sessionFactory = metaData.getSessionFactoryBuilder().build();
  }

  @Override
  public <T> SyncRepository<T> createRepository(Class<T> clazz) {
    return new SyncRepository<>(sessionFactory);
  }

  @Override
  public String getDriver() {
    return properties.getProperty("alphos-database-driver");
  }
}
