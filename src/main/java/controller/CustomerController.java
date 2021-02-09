package controller;

import model.Customer;
import model.Employee;
import repository.EmployeeRepository;
import repository.SalesRepository;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerController {
    private final ScannerExt scannerExt;

    private SalesRepository salesRepository;

    public CustomerController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.salesRepository = new SalesRepository(scannerExt);
    }
    public void listClient() {
        System.out.println("Lista e klienteve !");
        List<Customer> customers = salesRepository.list();
        List<Integer> choises = new ArrayList<>();
        int index = 1;
        for (Customer c : customers) {
            choises.add(index);
            System.out.println(index + "." + c.getFirstName() + " " + c.getLastName());
            index++;
        }
        choises.add(0);
        System.out.println("Zgjidh nje nga Klientet ose 0 per te shkuar mbrapa");
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
                Customer customer = customers.get(choise - 1);

                Integer operationChoise = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1, 2, 3));
                switch (operationChoise) {
                    case 1:
                        System.out.println(customer.printToConsole());
                        break;
                    case 2:
                        this.editCustomer(customer);
                        goback = false;
                        break;
                    case 3:
                        this.removeCustomer(customer);
                        goback = false;
                        break;
                    default:
                        goback = false;
                        break;
                }
            }
        }
    }
    public void addClient() {
        Customer customer=new Customer();
        EmployeeController employeeController = new EmployeeController(scannerExt);
        System.out.println("Vendosni emrin e klientit");
        customer.setFirstName(scannerExt.scanField());
        System.out.println("Vendosni mbiemrin e klientit");
        customer.setLastName( this.scannerExt.scanField());
        System.out.println("Vendosni emailin e klientit");
        customer.setEmail(this.scannerExt.scanField());
        System.out.println("Numri i kontaktit");
        customer.setPhone( scannerExt.scanNumberField());
        System.out.println("Adresa");
        customer.setAddress(this.scannerExt.scanField());
        System.out.println("Qyteti");
        customer.setCity(this.scannerExt.scanField());
        System.out.println("Shteti");
        customer.setCountry(this.scannerExt.scanField());
        customer.setEmployee(employeeController.setCurrentEmployee());
        salesRepository.save(customer);
    }
    public void editCustomer(Customer customer) {
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
                        customer.setFirstName(scannerExt.scanField());
                        break;
                    case 2:
                        System.out.println("Vendosni vleren e re");
                        customer.setLastName(scannerExt.scanField());
                        break;
                    case 3:
                        System.out.println("Vendosni vleren e re");
                        customer.setEmail(scannerExt.scanField());
                        break;
                    default:
                        isEditing = false;
                        back = false;
                        break;
                }
                firstEdit = false;
            }
            salesRepository.update(customer);
        }

    }

    public void removeCustomer(Customer customer) {
        salesRepository.delete(customer);
        System.out.println("Useri u fshi me sukses");

    }
}
