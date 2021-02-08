package repository;

import model.Customer;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.time.LocalDate;

public class OrderRepository extends AbstractRepository<Order>{
    private final ScannerExt scannerExt;

    public OrderRepository(ScannerExt scannerExt){
        this.scannerExt = scannerExt;
        this.aClass = Order.class;
    }

}