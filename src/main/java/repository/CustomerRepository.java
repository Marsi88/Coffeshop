package repository;

import model.Customer;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.List;
import java.util.Optional;

public class CustomerRepository extends AbstractRepository<Customer> {
    private final ScannerExt scannerExt;

    public CustomerRepository(ScannerExt scannerExt) {
        this.aClass = Customer.class;
        this.scannerExt = scannerExt;
    }

    public Optional<Customer> findByName(String firstName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select c from Customer c where c.firstName = :firstName and c.isDeleted=false");
        query.setParameter("firstName", firstName);
        List<Customer> customers = query.getResultList();
        session.close();
        if (!customers.isEmpty())
            return Optional.of(customers.get(0));
        return Optional.empty();
    }
}
