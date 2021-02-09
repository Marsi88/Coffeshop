
package controller;

import model.Customer;
import model.Order;
import repository.SalesRepository;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.ColorsUtils.ANSI_RESET;
import static util.ColorsUtils.ANSI_YELLOW_BACKGROUND;


public class SalesController {

    private final ScannerExt scannerExt;
    private SalesRepository salesRepository;
    private CustomerController customerController;
    private OrderController orderController;
    private ProductController productController;

    private static Customer currentCustomer;

    public SalesController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.salesRepository = new SalesRepository(scannerExt);
        this.customerController = new CustomerController(scannerExt);
        this.orderController = new OrderController(scannerExt);
        this.productController = new ProductController(scannerExt);
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void showSalesMenu() {
        boolean back = true;
        while (back) {
            System.out.println(ANSI_YELLOW_BACKGROUND + "Zgjidhni nje nga opsionet me poshte!" + ANSI_RESET);
            System.out.println("1.Manaxho Porosit");
            System.out.println("2.Manaxho Klientet");
            System.out.println("3.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            OrderController orderController = new OrderController(scannerExt);
            SalesController salesController = new SalesController(scannerExt);
            switch (choise) {
                case 1:
                    salesController.ManageOrders();
                    break;
                case 2:
                    salesController.ManageCustomer();
                    break;

                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void ManageOrders() {
        boolean back = true;
        while (back) {
            System.out.println("Zgjidhni nje nga opsionet me poshte : " +
                    "\n1.Listo porosi" +
                    "\n2.Shto porosi" +
                    "\n3.Ndrysho porosi" +
                    "\n4.Shko mbrapa");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2));
            switch (choise) {
                case 1:
//                    listOrders();
                    break;
                case 2:
                    Order order = orderController.addOrder();
                    productController.selectProduct(order);
                    break;
                case 3:
//                    editOrder();
                    break;
                case 4:
                    back = false;
                    break;
                default:
                    break;
            }


        }
    }

    public void ManageCustomer() {
        boolean back = true;
        while (back) {
            System.out.println("Zgjidhni nje nga opsionet me poshte" +
                    "\n1.Shto Klient" +
                    "\n2.Listo Klientet" +
                    "\n3.Back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            switch (choise) {
                case 1:
                    customerController.addClient();
                    break;
                case 2:
                    customerController.listClient();
                    break;

                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }
    }
}
