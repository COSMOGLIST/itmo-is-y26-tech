package ru.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.utils.SessionFactoryUsage;

public class OwnerDaoImp implements OwnerDao {
    public Owner findById(int id) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }
    public void add(Owner owner) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(owner);
        transaction.commit();
        session.close();
    }
    public void delete(Owner owner) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(owner);
        transaction.commit();
        session.close();
    }
    public void update(Owner owner) {
        Session session = SessionFactoryUsage.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(owner);
        transaction.commit();
        session.close();
    }
}
