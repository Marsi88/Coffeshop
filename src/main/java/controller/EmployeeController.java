package controller;

import model.Employee;
import repository.EmployeeRepository;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static util.ColorsUtils.*;

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
        getCurrentEmployee().getId();
        return getCurrentEmployee();
    }

    public void showAdminMenu() {
        boolean logout = true;
        while (logout) {
            System.out.println(ANSI_GREEN + "Zgjidhni nje nga opsionet me poshte!" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "1.Menaxho Punonjesit" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2.Menaxho  Shitjet" + ANSI_RESET);
            System.out.println(ANSI_RED + "3.Back !" + ANSI_RESET);
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));

            switch (choise) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    ProductController productsController = new ProductController(scannerExt);
                    productsController.manageProducts();
                case 3:
                    logout = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void manageEmployees() {
        boolean back = true;
        while (back) {
            System.out.println("Zgjidhni nje nga opsionet me poshte" +
                    "\n1.Shto punonjes" + "\n2.Listo punonjesi!" +
                    "\n3.Back!");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            switch (choise) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    listEmployees();
                    break;
                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }


    public void listEmployees() {
        System.out.println("Lista e punonjeseve !");
        List<Employee> employees = employeeRepository.list();
        List<Integer> choises = new ArrayList<>();
        int index = 1;
        for (Employee e : employees) {
            choises.add(index);
            System.out.println(index + "." + e.getFirstName() + " " + e.getLastName());
            index++;
        }
        choises.add(0);
        System.out.println("Zgjidh nje nga punonjesit ose 0 per te shkuar mbrapa");
        Integer choise = scannerExt.scanRestrictedFieldNumber(choises);
        if (choise == 0) {
            return;
        } else {
            boolean goback = true;
            while (goback) {
                System.out.println("Zgjidh nje nga opsionet" +
                        "\n0. Shko Mbrapa" +
                        "\n1.Shiko detajet e plota" +
                        "\n2.edit " +
                        "\n3.fshi");
                Employee employee = employees.get(choise - 1);

                Integer operationChoise = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1, 2, 3));
                switch (operationChoise) {
                    case 1:
                        System.out.println(employee.printToConsole());
                        break;
                    case 2:
                        this.editEmployee(employee);
                        goback = false;
                        break;
                    case 3:
                        this.removeEmployee(employee);
                        goback = false;
                        break;
                    default:
                        goback = false;
                        break;
                }
            }
        }
    }

    public void editEmployee(Employee employee) {
        boolean back = true;
        while (back) {
            boolean firstEdit = true;
            boolean isEditing = true;
            while (isEditing) {
                System.out.println("Zgjidh fushen" + (firstEdit ? "" : " tjeter") + " qe deshiron te ndryshosh" +
                        "\n 0.Shko Mbrapa" +
                        "\n 1.Emrin" +
                        "\n 2.Mbriemrin" +
                        "\n 3.Email");
                Integer editChoise = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1, 2, 3));
                switch (editChoise) {
                    case 1:
                        System.out.println("Vendosni vleren e re");
                        employee.setFirstName(scannerExt.scanField());
                        break;
                    case 2:
                        System.out.println("Vendosni vleren e re");
                        employee.setLastName(scannerExt.scanField());
                        break;
                    case 3:
                        System.out.println("Vendosni vleren e re");
                        employee.setEmail(scannerExt.scanField());
                        break;
                    default:
                        isEditing = false;
                        back = false;
                        break;
                }
                firstEdit = false;
            }
            employeeRepository.update(employee);
        }

    }

    public void removeEmployee(Employee employee) {
        employeeRepository.delete(employee);
        System.out.println("Useri u fshi me sukses");

    }


    public void addEmployee() {
        try {
            Employee employee = new Employee();

            System.out.println("Vendos Emrin e punonjesit e ri ");
            employee.setFirstName(this.scannerExt.scanField());
            System.out.println("Vendosni mbiemrin");
            employee.setLastName(this.scannerExt.scanField());
            System.out.println("Do jet Admin ose sales");
            employee.setRole(this.scannerExt.scanField());
            employee.setJobTitle(employee.getRole().equalsIgnoreCase("admin") ? "Administrator" : "Sales");
            boolean invalidUsername = true;
            while (invalidUsername) {
                System.out.println("Te vendosi username unik:");
                String username = this.scannerExt.scanField();
                if (employeeRepository.findByUsername(username).isPresent()) {
                    System.out.println("Username taken try another");
                } else {
                    invalidUsername = false;
                    employee.setUser(username);
                }
            }
            System.out.println("Te vendosi passwordin");
            employee.setPassword(scannerExt.scanField());

            employeeRepository.save(employee);
            System.out.println("Punonjesi u shtua");
        } catch (Exception e) {
            System.out.println("Pati nje problem provo perseri");
        }
    }
}
