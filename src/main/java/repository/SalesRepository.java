package repository;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

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

}

