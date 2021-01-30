package repository;

import model.Customer;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.time.LocalDate;

public class OrderRepository {

    public void listOrder() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select o from Order o where isActive=1");
        query.stream().forEach(System.out::println);
    }


    public void addOrder(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String status) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        Order order = new Order();
        order.setCustomer(customer);

        order.setOrderDate(orderDate);
        order.setRequiredDate(requiredDate);
        order.setShippedDate(shippedDate);
        order.setStatus(status);
        session.save(order);
        transaction.commit();
        session.close();
    }


}