package repository;

import model.Customer;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.time.LocalDate;
import java.util.List;

public class OrderRepository extends AbstractRepository<Order> {
    private final ScannerExt scannerExt;

    public OrderRepository(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.aClass = Order.class;
    }

    public List<Object[]> overview(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select distinct p.name,op.quantity,op.price from Product p  join p.orderProducts op  join op.order o where o.id= :orderid");
        query.setParameter("orderid", id);
        List<Object[]> overview = query.getResultList();
        session.close();
        return overview;

    }

}
