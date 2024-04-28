package de.dhbw.swe.main.database;

import com.google.inject.ImplementedBy;
import de.dhbw.swe.implementation.database.SyncRepository;
import de.dhbw.swe.internal.database.DefaultDatabase;

@ImplementedBy(DefaultDatabase.class)
public interface Database {

  void init(Class<?>... classes);

  <T> SyncRepository<T> createRepository(Class<T> clazz);

  String getDriver();
}
