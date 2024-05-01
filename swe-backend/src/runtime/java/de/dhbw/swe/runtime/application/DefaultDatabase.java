package de.dhbw.swe.runtime.application;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dhbw.swe.main.domain.valueObjects.DatabaseConfiguration;
import de.dhbw.swe.main.application.Database;
import de.dhbw.swe.runtime.inject.AutoBind;
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
@AutoBind(Database.class)
public class DefaultDatabase implements Database {

  private final DatabaseConfiguration databaseConfiguration;

  private SessionFactory sessionFactory;

  @Inject
  public DefaultDatabase(DatabaseConfiguration databaseConfiguration) {
    this.databaseConfiguration = databaseConfiguration;
  }

  @Override
  public void init(Class<?>... entities) {
    StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

    Map<String, Object> settings = new HashMap<>();
    settings.put(Environment.DRIVER, databaseConfiguration.getDatabaseDriver());
    settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");
    settings.put(Environment.URL, databaseConfiguration.getDatabaseUrl());
    settings.put(Environment.USER, databaseConfiguration.getDatabaseUser());
    settings.put(Environment.PASS, databaseConfiguration.getDatabasePassword());
    settings.put(Environment.HBM2DDL_AUTO, "update");
    settings.put(Environment.SHOW_SQL, false);
    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
    settings.put(Environment.ORDER_INSERTS, true);
    settings.put(Environment.ORDER_UPDATES, true);

    registryBuilder.applySettings(settings);

    StandardServiceRegistry registry = registryBuilder.build();
    MetadataSources metaDataSources = new MetadataSources(registry);

    Arrays.stream(entities).forEach(metaDataSources::addAnnotatedClass);

    Metadata metaData = metaDataSources.getMetadataBuilder().build();

    this.sessionFactory = metaData.getSessionFactoryBuilder().build();
  }

  @Override
  public SessionFactory getSession() {
    return this.sessionFactory;
  }

  @Override
  public String getDriver() {
    return databaseConfiguration.getDatabaseDriver();
  }
}
