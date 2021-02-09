package repository;

import model.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends AbstractEntity> {
    public Class<T> aClass;

    public List<T> list() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<T> list = session.createQuery("from " + aClass.getSimpleName() + " t where t.isDeleted = false").getResultList();
        session.close();
        return list;
    }

    public Optional<T> findById(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.close();
        return Optional.of(session.find(aClass, id));
    }

    public void save(T t) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(t);

        transaction.commit();
        session.close();
    }


    public void update(T t) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(t);
        transaction.commit();
        session.close();
    }

    public void delete(T t) {
        t.setIsDeleted(true);

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(t);

        transaction.commit();
        session.close();
    }
}
