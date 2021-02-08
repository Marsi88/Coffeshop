package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository extends AbstractRepository<Employee> {
    private final ScannerExt scannerExt;

    public EmployeeRepository(ScannerExt scannerExt) {
        this.aClass = Employee.class;
        this.scannerExt = scannerExt;
    }

    public Employee login(String username, String password) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query query = session.createQuery("from Employee e where e.user = :usersname and e.password = :password");

        query.setParameter("usersname", username);

        query.setParameter("password", password);

        Employee employee = null;

        List<Employee> employees = query.getResultList();
        session.close();
        if (!employees.isEmpty()) {
            employee = employees.get(0);
        }
        return employee;
    }


//    public void editEmployees() {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Query query = session.createQuery("select e from Employee e ");
//        List<Employee> employees = query.getResultList();
//        employees.forEach(System.out::println);
//
//    }


//    public List<Employee> getEmployeeIdNumbers() {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Query query = session.createQuery("SELECT employee.id from Employee e");
//        List<Employee> employees = query.getResultList();
//        return employees;
//    }
//
//    public List<Employee> list() {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Query query = session.createQuery("from Employee e where e.isworking=1");
//        List<Employee>employees=query.getResultList();
//        session.close();
//        return employees;
//    }
//
//    public Optional<Employee> findById(Integer id) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Query query = session.createQuery("from Employee e where e.employeeID=:id and e.isworking=1");
//        List<Employee> employees = query.getResultList();
//        if (!employees.isEmpty()) {
//            return Optional.of(employees.get(0));
//        }
//        session.close();
//        return Optional.empty();
//    }
//
//    public void save(Employee employee) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(employee);
//        transaction.commit();
//        session.close();
//    }
//
//    public void update(Employee employee) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(employee);
//        transaction.commit();
//        session.close();
//    }
//
//    public void delete(Employee employee) {
//        employee.setIsworking(2);
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(employee);
//        transaction.commit();
//        session.close();
//    }

    public Optional<Employee> findByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e where e.user = :usersname and e.isDeleted=false");
        query.setParameter("usersname", username);
        List<Employee> employees = query.getResultList();
        session.close();
        if (!employees.isEmpty())
            return Optional.of(employees.get(0));
        return Optional.empty();
    }
}
