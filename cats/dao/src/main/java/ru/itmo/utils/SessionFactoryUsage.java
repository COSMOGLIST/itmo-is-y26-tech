package ru.itmo.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;

public class SessionFactoryUsage {
    private static SessionFactory sessionFactory = null;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Cat.class);
                configuration.addAnnotatedClass(Owner.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
        return sessionFactory;
    }

    public static void close() {
        sessionFactory.close();
    }
}
