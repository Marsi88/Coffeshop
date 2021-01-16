package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static org.hibernate.SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();

//            configuration.addAnnotatedClass(Client.class);
//            configuration.addAnnotatedClass(Movie.class);
//            configuration.addAnnotatedClass(Reservation.class);
//            configuration.addAnnotatedClass(Room.class);
//            configuration.addAnnotatedClass(Schedule.class);
//            configuration.addAnnotatedClass(Seat.class);
//            configuration.addAnnotatedClass(Ticket.class);
//            configuration.addAnnotatedClass(TicketCategory.class);


            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory();


        }
        return sessionFactory;
    }
}

