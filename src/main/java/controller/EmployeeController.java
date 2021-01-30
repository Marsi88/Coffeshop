package controller;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.EmployeeRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static repository.Colors.*;

public class EmployeeController {

    private final ScannerExt scannerExt;

    private EmployeeRepository employeeRepository;

    private static Employee currentEmployee;

    public EmployeeController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.employeeRepository = new EmployeeRepository(scannerExt);
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void login() {
        boolean successfulLogin = true;
        while (successfulLogin) {
            System.out.println("Enter username or 0 to go back:");

            String username = this.scannerExt.scanField();

            if (username.equals("0")) {
                break;
            }

            System.out.println("Enter password:");

            String password = this.scannerExt.scanField();

            Employee employee = this.employeeRepository.login(username, password);
            System.out.println(employee.getEmployeeID());

            if (Objects.isNull(employee)) {
                System.out.println("Login i gabuar. Provo perseri");
            } else {
                currentEmployee = employee;
                successfulLogin = false;
                if (employee.getRole().equalsIgnoreCase("admin")) {
                    System.out.println("Opsionet e administratorit!");
                    showAdminMenu();
                } else {
                    System.out.println("Opsionet e Sales!");
                    SalesController salesController = new SalesController(this.scannerExt);
                    salesController.showSalesMenu();
                }
            }
        }
    }

    public Employee setCurrentEmployee() {
        employeeRepository.login(getCurrentEmployee().getUser(), getCurrentEmployee().getPassword());
        getCurrentEmployee().getEmployeeID();
        return getCurrentEmployee();
    }

    public void showAdminMenu() {
        boolean logout = true;
        while (logout) {
            SoutEmployeeOptions();
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));

            switch (choise) {
                case 1:
                    ManageEmployees();
                    break;
                case 2:
                    ProductsController productsController = new ProductsController(scannerExt);
                    productsController.ManageProducts();

//                case 2: {
//                    TableController tableController = new TableController(scannerExt);
//                    tableController.showMainMenu();
//                    break;
//                }
                case 3:
                    logout = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void SoutEmployeeOptions() {
        System.out.println(ANSI_GREEN + "Zgjidhni nje nga opsionet me poshte!");
        System.out.println(ANSI_CYAN + "1.Menaxho Punonjesit");
        System.out.println(ANSI_BLUE + "2.Menaxho  Shitjet");
        System.out.println(ANSI_RED + "3.Logout !");
    }


    public void ManageEmployees() {
        boolean back = true;
        while (back) {
            soutEmployeeOptions
                    ("Zgjidhni nje nga opsionet me poshte",
                            "1.Shto punonjes", "2.Listo punonjesi!",
                            "3.fshi punonjes", "4. ndrysho te dhenat e punonjesit",
                            "5.Back!");


            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));
            switch (choise) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    listEmployees();
                    break;

                case 3:
                    removeEmployee();
                    break;
                case 4:
                    editEmployee();
                    break;
                case 5:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void soutEmployeeOptions(String s, String s2, String s3, String s4, String s5, String s6) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
    }

    public void listEmployees() {
        System.out.println("Lista e punonjeseve !");
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e where isworking=1");
        List<Employee> employees = query.getResultList();
        employees.forEach(System.out::println);
        session.close();

    }

    public void addEmployee() {
        System.out.println("Vendos Emrin e punonjesit e ri ");
        String name = this.scannerExt.scanField();
        System.out.println("Vendosni mbiemrin");
        String lastname = this.scannerExt.scanField();
        System.out.println("Do jet Admin ose sales");
        String role = this.scannerExt.scanField();
        System.out.println("Cfare pozicioni do ket sales apo manager");
        String jobTittle = this.scannerExt.scanField();
        System.out.println("Te vendosi username unik:");
        String username = this.scannerExt.scanField();

        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select e.user from Employee e where e.user = :usersname");

        query.setParameter("usersname", username);
        List<Employee> employees = query.getResultList();
        if (!employees.isEmpty())
            System.out.println("user taken try another");
        username = scannerExt.scanField();

        System.out.println("Te vendosi passwordin");
        String password = this.scannerExt.scanField();

        Employee employee = new Employee();
        employee.setJobTitle(jobTittle);
        employee.setRole(role);
        employee.setFirstName(name);
        employee.setLastName(lastname);
        employee.setUser(username);
        employee.setPassword(password);
        employee.setIsworking(1);
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        System.out.println("Punonjesi u shtua");
        session.close();
    }

    public void editEmployee() {
        employeeRepository.editEmployees();
        boolean back = true;
        while (back) {
            System.out.println("\nWhat you want to edit?" +
                    "\n1. Name" +
                    "\n2 surname" +
                    "\n3 email" +
                    "\n4 username" +
                    "\n5 password" +
                    "\n6 Back To Work" +
                    "\n7 back");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

            switch (choise) {
                case 1:
                    System.out.println("Select an id to edit a punonjes");
                    Integer scanEmployeeId = scannerExt.scanNumberField();
                    System.out.println("enter new name");
                    String editName = scannerExt.scanField();
                    employeeRepository.editName(scanEmployeeId, editName);

                    break;

                case 2:

                    System.out.println("Select an id to edit a punonjes");
                    scanEmployeeId = scannerExt.scanNumberField();
                    System.out.println("enter new surname");
                    String editSurname = scannerExt.scanField();
                    employeeRepository.editSurname(scanEmployeeId, editSurname);
                    break;
                case 3:

                    System.out.println("Select an id to edit a punonjes");
                    scanEmployeeId = scannerExt.scanNumberField();
                    System.out.println("enter new Email");
                    String editEmail = scannerExt.scanField();
                    employeeRepository.editEmail(scanEmployeeId, editEmail);

                    break;
                case 4:

                    employeeRepository.editUserName();

                    break;
                case 5:

                    System.out.println("Select an id to edit a punonjes");
                    scanEmployeeId = scannerExt.scanNumberField();

                    System.out.println("enter new Password");
                    String editPassword = scannerExt.scanField();
                    employeeRepository.editPassword(scanEmployeeId, editPassword);
                    break;

                case 6:

                    System.out.println("Select an id to return back to work");
                    scanEmployeeId = scannerExt.scanNumberField();
                    employeeRepository.returnToWork(scanEmployeeId);
                    System.out.println("Employee has returned to work");

                    break;
                case 7:
                    back = false;
                    break;
            }


        }
    }

    public void removeEmployee() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select e from Employee e");
        query.stream().forEach(System.out::println);
        System.out.println("Select an id to delete a punonjes");
        EmployeeRepository employeeRepository=new EmployeeRepository(scannerExt);
        employeeRepository.getEmployeeIdNumbers();
        Integer scanEmployeeId = scannerExt.scanNumberField();
        Employee employee = session.find(Employee.class, scanEmployeeId);
        employee.setIsworking(2);
        session.update(employee);
        transaction.commit();
        session.close();

    }


}
