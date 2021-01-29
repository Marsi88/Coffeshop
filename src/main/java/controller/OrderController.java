package controller;

import model.Customer;
import model.Order;
import model.OrderProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.ScannerExt;

import java.time.LocalDate;

public class OrderController {

    private final ScannerExt scannerExt;

    public OrderController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
    }

    public void showMyOrders() {

    }

    public void addOrder() {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        System.out.println("Zgjidhni Klientin nga iD");
//        Integer scanCustomerId = scannerExt.scanNumberField();
//        Customer customer = new Customer();
//        Order order = new Order();
//        order.setCustomer(customer);
//        customer.setCustomerID(scanCustomerId);
////        System.out.println("set order Id");
////        Integer orderId=scannerExt.scanNumberField();
//        OrderProduct orderProduct=new OrderProduct();
////        orderProduct.getOrder().setOrderId(scanCustomerId);
//        orderProduct.setOrderProductId(1);
//        System.out.println("Vendosni daten e berjes se porosise");
//        LocalDate orderDate = scannerExt.scanDateField();
//        System.out.println("Vendosni daten e dorezimit");
//        LocalDate requiredDate = scannerExt.scanDateField();
//        System.out.println("Vendosni daten kur u dorezua");
//        LocalDate shippedDate = scannerExt.scanDateField();
//        System.out.println("Vendosni statusin");
//        String status = this.scannerExt.scanField();
//        order = new Order();
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
}