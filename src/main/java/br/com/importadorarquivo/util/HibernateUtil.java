package br.com.importadorarquivo.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

      private static final SessionFactory sessionFactory = buildSessionFactory();

      private static SessionFactory buildSessionFactory() {
            try {
                  Configuration configuration = new Configuration().configure();

                  StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

                  serviceRegistryBuilder.applySettings(configuration.getProperties());

                  ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

                  return configuration.buildSessionFactory(serviceRegistry);

            } catch (HibernateException ex) {
                  // Make sure you log the exception, as it might be swallowed
                  System.err.println("Initial SessionFactory creation failed." + ex);
                  throw new ExceptionInInitializerError(ex);
            }
      }

      public static SessionFactory getSessionFactory() {
            return sessionFactory;
      }

}
