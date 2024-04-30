package de.dhbw.swe.main.domain.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SyncRepository<T> {

  private final SessionFactory sessionFactory;

  public SyncRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void save(T t) {
    try (Session session = getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.saveOrUpdate(t);
      transaction.commit();
    }
  }

  public void delete(T t) {
    try (Session session = getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.delete(t);
      transaction.commit();
    }
  }

  public void update(T t) {
    try (Session session = getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.saveOrUpdate(t);
      transaction.commit();
    }
  }

  public T get(long id, Class<T> clazz) {
    try (Session session = getSessionFactory().openSession()) {
      return session.get(clazz, id);
    } catch (Exception exception) {
      return null;
    }
  }
  public T get(String id, Class<T> clazz) {
    try (Session session = getSessionFactory().openSession()) {
      return session.get(clazz, id);
    } catch (Exception exception) {
      return null;
    }
  }

  public List<T> load(Class<T> clazz) {
    try (Session session = getSessionFactory().openSession()) {
      return loadAllData(clazz, session);
    }
  }

  private List<T> loadAllData(Class<T> type, Session session) {
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<T> criteria = builder.createQuery(type);
    criteria.from(type);
    return session.createQuery(criteria).getResultList();
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
