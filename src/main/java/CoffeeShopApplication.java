import model.Employee;
import org.hibernate.Session;
import repository.EmployeeRepository;
import util.HibernateUtils;

import java.util.Scanner;

public class CoffeeShopApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Session session = HibernateUtils.getSessionFactory().openSession();

        session = HibernateUtils.getSessionFactory().openSession();
        session.createQuery("FROM Product").getResultList().forEach(System.out::println);

        System.out.println("WELCOME TO OUR COFFEESHOP MAGAZINE");
        System.out.println(" Log in or quit ");
        Boolean invalid = true;
        do {
            System.out.println("enter username");
            String username = scanner.nextLine();
            System.out.println("enter password");
            String password = scanner.nextLine();
            Employee employee = EmployeeRepository.login(username, password);

            if (employee == null) {
                System.out.println("Provo perseri");
            } else if (employee.getRole().equalsIgnoreCase("admin")) {
                System.out.println("Show admin menu");
                invalid = false;
            } else if (employee.getRole().equalsIgnoreCase("sales")) {
                System.out.println("show sale menu");
                invalid = false;
            }

        } while (invalid);
    }
}
