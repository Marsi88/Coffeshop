package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

        if (!employees.isEmpty()) {
            employee = employees.get(0);
        }

        session.close();

        return employee;
    }

    public void editUserName() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Select an id to edit a punonjes");
        Integer scanEmployeeId = scannerExt.scanNumberField();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        System.out.println("enter new username");
        String editUsername = scannerExt.scanField();
        Query query = session.createQuery("select e.user from Employee e where e.user = :usersname");
        query.setParameter("usersname", editUsername);
        List<Employee> employees2 = query.getResultList();
        if (!employees2.isEmpty())
            System.out.println("user taken try another");
        editUsername = scannerExt.scanField();
        employee.setUser(editUsername);
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public void editEmployees() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e ");
        List<Employee> employees = query.getResultList();
        employees.forEach(System.out::println);

    }

    public void editName(Integer scanEmployeeId, String editName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        employee.setFirstName(editName);
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public void editSurname(Integer scanEmployeeId, String editSurname) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        employee.setLastName(editSurname);
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public void editEmail(Integer scanEmployeeId, String editEmail) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        employee.setEmail(editEmail);
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public void editPassword(Integer scanEmployeeId, String editPassword) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        employee.setPassword(editPassword);
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public void returnToWork(Integer scanEmployeeId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        employee.setIsworking(1);
        session.update(employee);
        transaction.commit();
        session.close();
    }

    public List<Employee> getEmployeeIdNumbers() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT employee.id from Employee e");
        List<Employee> employees = query.getResultList();
        return employees;
    }
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
        Query query = session.createQuery("select e from Employee e where e.user = :usersname");
        query.setParameter("usersname", username);
        List<Employee> employees = query.getResultList();
        if (!employees.isEmpty())
            return Optional.of(employees.get(0));
        return Optional.empty();
    }
}
