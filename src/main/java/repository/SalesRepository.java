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
import java.util.Optional;

public class SalesRepository extends AbstractRepository<Customer> {
    private final ScannerExt scannerExt;

    public SalesRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.aClass = Customer.class;
    }

    public Customer customer(Integer customerID, String firstName) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("select c from Customer c where c.id = :customerID and c.firstName = :firstName");

        query.setParameter("customerID", customerID);

        query.setParameter("firstName", firstName);

        Customer customer = null;

        List<Customer> customers = query.getResultList();

        if (!customers.isEmpty()) {
            customer = customers.get(0);
        }
        session.close();

        return customer;
    }

    public void editCustomer() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c where  c.isDeleted=false");
        List<Customer> customers = query.getResultList();
        customers.forEach(System.out::println);
        session.close();
    }
    public Optional<Customer> findByCustomerFirstName(String firstName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c where c.firstName = :firstName and c.isDeleted=false");
        query.setParameter("firstName", firstName);
        List<Customer> customers = query.getResultList();
        session.close();
        if (!customers.isEmpty())
            return Optional.of(customers.get(0));
        return Optional.empty();
    }

    public void listCustomer() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select distinct c from Customer c where c.isDeleted=false");
        List<Customer> listClient = query.getResultList();
        listClient.forEach(System.out::println);
        session.close();
    }

}

