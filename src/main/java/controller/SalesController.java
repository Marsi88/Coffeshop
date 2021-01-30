package controller;

import model.Customer;
import model.Employee;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.SalesRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.time.LocalDate;
import java.util.Arrays;

import static repository.Colors.ANSI_YELLOW_BACKGROUND;


public class SalesController {

    private final ScannerExt scannerExt;

    private SalesRepository salesRepository;

    private static Customer currentCustomer;

    public SalesController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.salesRepository = new SalesRepository();
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void showSalesMenu() {
        boolean back = true;
        while (back) {
            System.out.println(ANSI_YELLOW_BACKGROUND + "Zgjidhni nje nga opsionet me poshte!");
            System.out.println("1.Porosi");
            System.out.println("2.Klient");
            System.out.println("3.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            OrderController orderController = new OrderController(scannerExt);
            SalesController salesController = new SalesController(scannerExt);
            switch (choise) {
                case 1:
                    salesController.ManageOrders();
                    break;
//                case 2:
//                    orderController.addOrder();
//                    break;
                case 2:
                    salesController.ManageClient();
                    break;

                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void soutOrderOptions(String s, String s2, String s3){
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);

    }

    public void ManageOrders(){
        boolean back = true;
        while (back){
            soutOrderOptions("Zgjidhni nje nga opsionet me poshte",
                    "1.Listo porosi",
                    "2.Shto porosi");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2));
            switch (choise){
                case 1:
                    listOrders();
                    break;

                case 2:
                    addOrder();
                    break;
            }

        }
    }

    private void soutClientOptions(String s, String s2, String s3, String s4, String s5, String s6) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
    }


    public void ManageClient() {
        boolean back = true;
        while (back) {
            soutClientOptions
                    ("Zgjidhni nje nga opsionet me poshte",
                            "1.Shto Klient", "2.Listo Klient!",
                            "3.Fshi Klient", "4.Ndrysho te dhenat e Klienteve",
                            "5.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));
            switch (choise) {
                case 1:
                    addClient();
                    break;
                case 2:
                    listClient();
                    break;

                case 3:
                    removeClient();
                    break;
                case 4:
                    editClient();
                    break;
                case 5:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void listClient() {
        System.out.println("Lista e klienteve !");
salesRepository.listClient().forEach(System.out::println);

    }

    public void addClient() {
        System.out.println("Vendosni emrin e klientit");
        String name = this.scannerExt.scanField();
        System.out.println("Vendosni mbiemrin e klientit");
        String lastname = this.scannerExt.scanField();
        System.out.println("Vendosni emailin e klientit");
        String email = this.scannerExt.scanField();
        System.out.println("Numri i kontaktit");
        Integer phone = scannerExt.scanNumberField();
        System.out.println("Adresa");
        String address = this.scannerExt.scanField();
        System.out.println("Qyteti");
        String city = this.scannerExt.scanField();
        System.out.println("Shteti");
        String country = this.scannerExt.scanField();

        Customer customer = new Customer();
        EmployeeController employeeController = new EmployeeController(scannerExt);
        employeeController.setCurrentEmployee();
        customer.setEmployee(employeeController.setCurrentEmployee());
        customer.setFirstName(name);
        customer.setLastName(lastname);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setCountry(country);
        salesRepository.addClient(customer);
    }

    public void editClient() {
        salesRepository.editClient();

        boolean back = true;
        while (back) {
            System.out.println("\nCfare doni te modifikoni?" +
                    "\n1.Emrin" +
                    "\n2 Mbiemrin" +
                    "\n3 Emailin" +
                    "\n4 Numrin e Kontaktit" +
                    "\n5 Adresen" +
                    "\n6 Qytetin" +
                    "\n7 Shtetin" +
                    "\n8 back");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

            switch (choise) {
                case 1:
                   salesRepository.editName(scannerExt);
                    break;

                case 2:
                    salesRepository.editLastname(scannerExt);
                    break;

                case 3:
                    salesRepository.editEmail(scannerExt);
                    break;

                case 4:
                    salesRepository.editPhone(scannerExt);
                    break;

                case 5:
                    salesRepository.editAddress(scannerExt);
                    break;

                case 6:
                    salesRepository.editCity(scannerExt);
                    break;

                case 7:
                    salesRepository.editCountry(scannerExt);
                    break;
                case 8:
                    back = false;
                    break;
            }
        }
    }
    public void removeClient() {
        salesRepository.removeClient(scannerExt);
    }

    public void  listOrders() {
        salesRepository.listOrders().forEach(System.out::println);

    }

    public void addOrder() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

       // salesRepository.listClient().forEach(System.out::println);

        System.out.println("Vendosni id e klientit");

        Integer customerId = this.scannerExt.scanNumberField();

Customer customer=new Customer();
customer.setCustomerID(customerId);



        System.out.println("Vendosni daten e berjes se porosise");
        LocalDate orderDate = scannerExt.scanDateField();
        System.out.println("Vendosni daten e dorezimit");
        LocalDate requiredDate = scannerExt.scanDateField();
        System.out.println("Vendosni daten kur u dorezua");
        LocalDate shippedDate = scannerExt.scanDateField();
        System.out.println("Vendosni statusin");
        String status = this.scannerExt.scanField();

        Order order = new Order();
order.setCustomer(customer);

        order.setOrderDate(orderDate);
        order.setRequiredDate(requiredDate);
        order.setShippedDate(shippedDate);
        order.setStatus(status);
        session.save(order);
        transaction.commit();
        System.out.println("Porosia u shtua");
        session.close();
    }

}

