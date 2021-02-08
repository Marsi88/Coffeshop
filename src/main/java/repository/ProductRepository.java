package repository;

import model.Employee;
import model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.List;

public class ProductRepository extends AbstractRepository<Product> {
    private final ScannerExt scannerExt;

    public ProductRepository(ScannerExt scannerExt) {
        this.aClass = Product.class;
        this.scannerExt = scannerExt;
    }

    public List<Product> listByProductLineId(int productLineId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select p from Product p where p.productLine.id = :id and p.isDeleted=false");
        query.setParameter("id", productLineId);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }
}
