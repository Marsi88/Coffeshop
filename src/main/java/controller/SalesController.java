package controller;

import model.Customer;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.SalesRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.Arrays;
import java.util.List;

import static repository.Colors.ANSI_YELLOW_BACKGROUND;


public class SalesController {

    private final ScannerExt scannerExt;

    private SalesRepository salesRepository;

    private static Customer currentCustomer;

    public SalesController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.salesRepository = new SalesRepository();
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void showSalesMenu() {
        boolean back = true;
        while (back) {
            System.out.println(ANSI_YELLOW_BACKGROUND + "Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Porosi");
            System.out.println("2.Klient");
            System.out.println("3.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            OrderController orderController = new OrderController(scannerExt);
            SalesController salesController = new SalesController(scannerExt);
            switch (choise) {
                case 1:
                    orderController.showMyOrders();
                    break;
//                case 2:
//                    orderController.addOrder();
//                    break;
                case 2:
                    salesController.ManageClient();
                    break;

                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void soutClientOptions(String s, String s2, String s3, String s4, String s5, String s6) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
    }

    public void ManageClient() {
        boolean back = true;
        while (back) {
            soutClientOptions
                    ("Zgjidhni nje nga opsionet me poshte",
                            "1.Shto Klient", "2.Listo Klient!",
                            "3.Fshi Klient", "4.Ndrysho te dhenat e Klienteve",
                            "5.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));
            switch (choise) {
                case 1:
                    addClient();
                    break;
                case 2:
                    listClient();
                    break;

                case 3:
                    removeClient();
                    break;
                case 4:
                    editClient();
                    break;
                case 5:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void listClient() {
        System.out.println("Lista e klienteve !");
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c where c.isActive=1");
        List<Customer> customers = query.getResultList();
        customers.forEach(System.out::println);
        session.close();
    }

    public void addClient() {
        System.out.println("Vendosni emrin e klientit");
        String name = this.scannerExt.scanField();
        System.out.println("Vendosni mbiemrin e klientit");
        String lastname = this.scannerExt.scanField();
        System.out.println("Vendosni emailin e klientit");
        String email = this.scannerExt.scanField();
        System.out.println("Numri i kontaktit");
        Integer phone = scannerExt.scanNumberField();
        System.out.println("Adresa");
        String address = this.scannerExt.scanField();
        System.out.println("Qyteti");
        String city = this.scannerExt.scanField();
        System.out.println("Shteti");
        String country = this.scannerExt.scanField();

        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c");

//        query.setParameter("usersname", username);
//        List<Employee> employees = query.getResultList();
//        if (!employees.isEmpty())
//            System.out.println("user taken try another");
//        username = scannerExt.scanField();
//
//        System.out.println("Te vendosi passwordin");
//        String password = this.scannerExt.scanField();

        Customer customer = new Customer();
        EmployeeController employeeController = new EmployeeController(scannerExt);
        employeeController.setCurrentEmployee();
        customer.setEmployee(employeeController.setCurrentEmployee());
        customer.setFirstName(name);
        customer.setLastName(lastname);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setCountry(country);
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        System.out.println("Klienti u shtua");
        session.close();
    }

    public void editClient() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c ");
        List<Customer> customers = query.getResultList();
        customers.forEach(System.out::println);

        boolean back = true;
        while (back) {
            System.out.println("\nCfare doni te modifikoni?" +
                    "\n1.Emrin" +
                    "\n2 Mbiemrin" +
                    "\n3 Emailin" +
                    "\n4 Numrin e Kontaktit" +
                    "\n5 Adresen" +
                    "\n6 Qytetin" +
                    "\n7 Shtetin" +
                    "\n8 back");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

            switch (choise) {
                case 1:
                    Transaction transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    Integer scanCustomerId = scannerExt.scanNumberField();
                    Customer customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani emrin e ri");
                    String editName = scannerExt.scanField();
                    customer.setFirstName(editName);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;

                case 2:
                    transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    scanCustomerId = scannerExt.scanNumberField();
                    customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani mbiemrin e ri");
                    String editSurname = scannerExt.scanField();
                    customer.setLastName(editSurname);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;

                case 3:
                    transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    scanCustomerId = scannerExt.scanNumberField();
                    customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani emailin e ri");
                    String editEmail = scannerExt.scanField();
                    customer.setEmail(editEmail);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;

                case 4:
                    transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    scanCustomerId = scannerExt.scanNumberField();
                    customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani numrin e ri");
                    Integer editphone = Integer.valueOf(scannerExt.scanField());
                    customer.setPhone(editphone);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;

                case 5:
                    transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    scanCustomerId = scannerExt.scanNumberField();
                    customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani adresen e re");
                    String editAddress = scannerExt.scanField();
                    customer.setAddress(editAddress);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;

                case 6:
                    session = HibernateUtils.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    scanCustomerId = scannerExt.scanNumberField();
                    customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani qytetin e ri");
                    String editCity = scannerExt.scanField();
                    customer.setCity(editCity);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;

                case 7:
                    session = HibernateUtils.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
                    scanCustomerId = scannerExt.scanNumberField();
                    customer = session.find(Customer.class, scanCustomerId);
                    System.out.println("Shkruani shtetin e ri");
                    String editCountry = scannerExt.scanField();
                    customer.setCountry(editCountry);
                    session.update(customer);
                    transaction.commit();
                    System.out.println("Klienti u modifikua");
                    session.close();
                    break;
                case 8:
                    back = false;
                    break;
            }
        }
    }

    public void removeClient() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select c from Customer c");
        query.stream().forEach(System.out::println);
        System.out.println("Zgjidhni id per te fshire nje klient");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        session.update(customer);
        customer.setIsActive(2);
        transaction.commit();
        System.out.println("Klienti u fshi");
        session.close();
    }
}

