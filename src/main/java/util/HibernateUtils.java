package util;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static org.hibernate.SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Office.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(OrderProduct.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(ProductLine.class);


            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory();


        }
        return sessionFactory;
    }
}

