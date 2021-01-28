package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class EmployeeRepository {




    public Employee login(String username, String password){
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("from Employee e where e.user = :usersname and e.password = :password");

        query.setParameter("usersname", username);

        query.setParameter("password", password);

        Employee employee = null;

        List<Employee> employees = query.getResultList();

        if(!employees.isEmpty()){
            employee = employees.get(0);
        }

        session.close();

        return employee;
    }

}
