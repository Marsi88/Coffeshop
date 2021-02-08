
package controller;

import model.Customer;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.CustomerRepository;
import repository.SalesRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.time.LocalDate;
import java.util.Arrays;

import static util.ColorsUtils.ANSI_RESET;
import static util.ColorsUtils.ANSI_YELLOW_BACKGROUND;


public class SalesController {

    private final ScannerExt scannerExt;

    private CustomerRepository customerRepository;

    private static Customer currentCustomer;

    public SalesController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.customerRepository = new CustomerRepository(scannerExt);
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void showSalesMenu() {
        boolean back = true;
        while (back) {
            System.out.println(ANSI_YELLOW_BACKGROUND + "Zgjidhni nje nga opsionet me poshte!" + ANSI_RESET);
            System.out.println("1.Porosit");
            System.out.println("2.Klientet");
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

    private void soutOrderOptions(String s, String s2, String s3) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);

    }

    public void ManageOrders() {
        boolean back = true;
        while (back) {
            soutOrderOptions("Zgjidhni nje nga opsionet me poshte",
                    "1.Listo porosi",
                    "2.Shto porosi");
//            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2));
//            switch (choise) {
//                case 1:
//                    listOrders();
//                    break;
//
//                case 2:
//                    addOrder();
//                    break;
//            }

        }
    }


    public void ManageClient() {
        boolean back = true;
        while (back) {
            System.out.println("Zgjidhni nje nga opsionet me poshte" +
                    "1.Shto Klient" +
                    "2.Listo Klientet" +
                    "3.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));
            switch (choise) {
                case 1:
                    addClient();
                    break;
                case 2:
                    listClient();
                    break;

                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void listClient() {
        System.out.println("Lista e klienteve !");
        customerRepository.list();

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
        customerRepository.save(customer);
    }

//    public void editClient() {
//
//        boolean back = true;
//        while (back) {
//            System.out.println("\nCfare doni te modifikoni?" +
//                    "\n1.Emrin" +
//                    "\n2 Mbiemrin" +
//                    "\n3 Emailin" +
//                    "\n4 Numrin e Kontaktit" +
//                    "\n5 Adresen" +
//                    "\n6 Qytetin" +
//                    "\n7 Shtetin" +
//                    "\n8 back");
//
//            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
//
//            switch (choise) {
//                case 1:
//                    System.out.println("Zgjidhni nje Id per te modifikuar klientin");
//                    Integer scanCustomerId = scannerExt.scanNumberField();
//                    System.out.println("Shkruani emrin e ri");
//                    String editName = scannerExt.scanField();
//                    salesRepository.editName(scanCustomerId, editName);
//
//                    break;
//
//                case 2:
//                    salesRepository.editLastname(scannerExt);
//                    break;
//
//                case 3:
//                    salesRepository.editEmail(scannerExt);
//                    break;
//
//                case 4:
//                    salesRepository.editPhone(scannerExt);
//                    break;
//
//                case 5:
//                    salesRepository.editAddress(scannerExt);
//                    break;
//
//                case 6:
//                    salesRepository.editCity(scannerExt);
//                    break;
//
//                case 7:
//                    salesRepository.editCountry(scannerExt);
//                    break;
//                case 8:
//                    back = false;
//                    break;
//            }
//        }
//    }
//
//    public void removeClient() {
//        salesRepository.removeClient(scannerExt);
//    }
//
//    public void listOrders() {
//        System.out.println("Listo Porosit");
//        salesRepository.listOrder();
//    }
//
//    public void addOrder() {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//        // salesRepository.listClient().forEach(System.out::println);
//
//        System.out.println("Vendosni id e klientit");
//
//        Integer customerId = this.scannerExt.scanNumberField();
//
//        Customer customer = new Customer();
//        customer.setId(customerId);
//
//
//        System.out.println("Vendosni daten e berjes se porosise");
//        LocalDate orderDate = scannerExt.scanDateField();
//        System.out.println("Vendosni daten e dorezimit");
//        LocalDate requiredDate = scannerExt.scanDateField();
//        System.out.println("Vendosni daten kur u dorezua");
//        LocalDate shippedDate = scannerExt.scanDateField();
//        System.out.println("Vendosni statusin");
//        String status = this.scannerExt.scanField();
//
//        Order order = new Order();
//        order.setCustomer(customer);
//
//        order.setOrderDate(orderDate);
//        order.setRequiredDate(requiredDate);
//        order.setShippedDate(shippedDate);
//        order.setStatus(status);
//        session.save(order);
//        transaction.commit();
//        System.out.println("Porosia u shtua");
//        session.close();
//    }

}

//