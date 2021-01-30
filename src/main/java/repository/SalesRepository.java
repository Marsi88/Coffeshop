package repository;

import com.mysql.cj.xdevapi.Client;
import model.Customer;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.List;

public class SalesRepository {
    public Customer customer(Integer customerID, String firstName){
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("from Customer c where c.customerID = :customerID and c.firstname = :firstName");

        query.setParameter("customerID", customerID);

        query.setParameter("firstname", firstName);

        Customer customer = null;

        List<Customer> customers = query.getResultList();

        if(!customers.isEmpty()){
            customer = customers.get(0);
        }

        session.close();

        return customer;
    }
    public void editClient() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c ");
        List<Customer> customers = query.getResultList();
        customers.forEach(System.out::println);
    }
    public List<Customer>  listClient() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c where c.isActive=1");
        List<Customer> listClient = query.getResultList();
        session.close();
        return listClient();
    }
    public void addClient(Customer customer) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c");
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        System.out.println("Klienti u shtua");
        session.close();
    }
    public void removeClient(ScannerExt scannerExt) {
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

    public void editName(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
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
    }

    public void editLastname(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Zgjidhni nje Id per te modifikuar klientin");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        System.out.println("Shkruani mbiemrin e ri");
        String editSurname = scannerExt.scanField();
        customer.setLastName(editSurname);
        session.update(customer);
        transaction.commit();
        System.out.println("Klienti u modifikua");
        session.close();
    }


    public void editEmail(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Zgjidhni nje Id per te modifikuar klientin");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        System.out.println("Shkruani emailin e ri");
        String editEmail = scannerExt.scanField();
        customer.setEmail(editEmail);
        session.update(customer);
        transaction.commit();
        System.out.println("Klienti u modifikua");
        session.close();
    }

    public void editPhone(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Zgjidhni nje Id per te modifikuar klientin");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        System.out.println("Shkruani numrin e ri");
        Integer editphone = Integer.valueOf(scannerExt.scanField());
        customer.setPhone(editphone);
        session.update(customer);
        transaction.commit();
        System.out.println("Klienti u modifikua");
        session.close();
    }

    public void editAddress(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Zgjidhni nje Id per te modifikuar klientin");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        System.out.println("Shkruani adresen e re");
        String editAddress = scannerExt.scanField();
        customer.setAddress(editAddress);
        session.update(customer);
        transaction.commit();
        System.out.println("Klienti u modifikua");
        session.close();
    }

    public void editCity(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Zgjidhni nje Id per te modifikuar klientin");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        System.out.println("Shkruani qytetin e ri");
        String editCity = scannerExt.scanField();
        customer.setCity(editCity);
        session.update(customer);
        transaction.commit();
        System.out.println("Klienti u modifikua");
        session.close();
    }

    public void editCountry(ScannerExt scannerExt) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction  transaction = session.beginTransaction();
        System.out.println("Zgjidhni nje Id per te modifikuar klientin");
        Integer scanCustomerId = scannerExt.scanNumberField();
        Customer customer = session.find(Customer.class, scanCustomerId);
        System.out.println("Shkruani shtetin e ri");
        String editCountry = scannerExt.scanField();
        customer.setCountry(editCountry);
        session.update(customer);
        transaction.commit();
        System.out.println("Klienti u modifikua");
        session.close();
    }

    public List<Order>  listOrders() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Order c where c.isActive=1");
        List<Customer> listClient = query.getResultList();
        session.close();
        return listOrders();
    }



}

