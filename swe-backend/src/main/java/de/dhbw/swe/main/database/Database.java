package de.dhbw.swe.main.database;

public interface Database {

  void init(Class<?>... classes);

  <T> SyncRepository<T> createRepository(Class<T> clazz);

  String getDriver();
}
