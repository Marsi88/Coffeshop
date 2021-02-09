package repository;

import model.Order;
import model.OrderProduct;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

public class OrderProductRepository extends AbstractRepository<OrderProduct> {
    private final ScannerExt scannerExt;

    public OrderProductRepository(ScannerExt scannerExt) {
        this.aClass = OrderProduct.class;
        this.scannerExt = scannerExt;
    }

 public void addOrderProduct(Order order,Product product){
     Session session=HibernateUtils.getSessionFactory().openSession();
     Transaction transaction=session.beginTransaction();
     transaction.commit();
 }
    }
