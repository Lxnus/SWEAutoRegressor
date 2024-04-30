package de.dhbw.swe.main.application;

import org.hibernate.SessionFactory;

public interface Database {

  void init(Class<?>... entities);

  SessionFactory getSession();

  String getDriver();
}
