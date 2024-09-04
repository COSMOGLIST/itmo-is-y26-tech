package ru.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.models.Cat;
import ru.itmo.utils.SessionFactoryUsage;

public class CatDaoImp implements CatDao {
    public Cat findById(int id) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Cat cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }
    public void add(Cat cat) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(cat);
        transaction.commit();
        session.close();
    }
    public void delete(Cat cat) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cat);
        transaction.commit();
        session.close();
    }
    public void update(Cat cat) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(cat);
        transaction.commit();
        session.close();
    }
}
