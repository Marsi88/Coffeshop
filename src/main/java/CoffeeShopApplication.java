import org.hibernate.Session;
import util.HibernateUtils;

import java.util.Scanner;

public class CoffeeShopApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Session session = HibernateUtils.getSessionFactory().openSession();

        session = HibernateUtils.getSessionFactory().openSession();
        session.createQuery("FROM Product").getResultList().forEach(System.out::println);

        System.out.println("WELCOME TO OUR COFFEESHOP MAGAZINE");
        System.out.println(" Press 1 to Log in or 2. to quit ");
        String identifyPerson = scanner.nextLine();


    }
}
