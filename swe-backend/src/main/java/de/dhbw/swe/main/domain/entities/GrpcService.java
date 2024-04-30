package de.dhbw.swe.main.domain.entities;

import org.hibernate.SessionFactory;

public interface GrpcService {

    void setSessionFactory(SessionFactory sessionFactory);

    SessionFactory getSessionFactory();
}
