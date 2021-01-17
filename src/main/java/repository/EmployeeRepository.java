package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

public class EmployeeRepository {

    public static Employee login (String user, String password) {
        try{
            Session session = HibernateUtils.getSessionFactory().openSession();
            Query query = session.createQuery("from Employee e where e.user =:username and e.password=:password");
            query.setParameter("username", user);
            query.setParameter("password", password);
            return (Employee) query.getSingleResult();
        }catch (Exception e){
            return null;
        }

    }

}
