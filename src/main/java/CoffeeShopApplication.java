import org.hibernate.Session;
import util.HibernateUtils;

public class CoffeeShopApplication {
    public static void main(String[] args) {


     Session session = HibernateUtils.getSessionFactory().openSession();

        session = HibernateUtils.getSessionFactory().openSession();
        session.createQuery("FROM Product").getResultList().forEach(System.out::println);

    }
}
