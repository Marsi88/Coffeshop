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


}
