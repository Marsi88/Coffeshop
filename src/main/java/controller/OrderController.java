package controller;

import model.Customer;
import model.Order;
import model.OrderProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.OrderRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.time.LocalDate;

public class OrderController {

    private final ScannerExt scannerExt;
    private OrderRepository orderRepository;

    public OrderController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.orderRepository = new OrderRepository(scannerExt);
    }

    public void listOrders() {


    }


    public Order addOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        System.out.println("Vendosni daten qe do klienti te dorezohet porosia");
        order.setRequiredDate(scannerExt.scanDateField());
        System.out.println("Vendosni daten kur u dorezua");
        order.setShippedDate(scannerExt.scanDateField());
        System.out.println("Vendosni statusin");
        order.setStatus(scannerExt.scanField());
        orderRepository.save(order);
return order;
    }

}
