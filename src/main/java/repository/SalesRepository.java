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
    public Customer customer(Integer customerID, String firstName) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("from Customer c where c.id = :customerID and c.firstname = :firstName");

        query.setParameter("customerID", customerID);

        query.setParameter("firstname", firstName);

        Customer customer = null;

        List<Customer> customers = query.getResultList();

        if (!customers.isEmpty()) {
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

    public void listClient() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select distinct c from Customer c where c.isActive=1");
        List<Customer> listClient = query.getResultList();
        listClient.forEach(System.out::println);
        session.close();
    }

}

