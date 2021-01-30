package repository;

import model.Employee;
import model.ProductLine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;
import java.util.Optional;

public class ProductLinesRepository implements Repository<ProductLine> {


    @Override
    public List<ProductLine> list() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery(" from ProductLine pl");
        List<ProductLine> productLines = query.getResultList();
        session.close();
        return productLines;

    }

    @Override
    public Optional<ProductLine> findById(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("from ProductLine pl where pl.idProductLines=:id");
        List<ProductLine> productLines = query.getResultList();
        if (!productLines.isEmpty()) {
            return Optional.of(productLines.get(0));
        }
        session.close();
        return Optional.empty();
    }

    @Override
    public void save(ProductLine productLine) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(productLine);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(ProductLine productLine) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(productLine);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(ProductLine productLine) {
    }
}
