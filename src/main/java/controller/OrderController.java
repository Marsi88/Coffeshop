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
import java.util.List;

public class OrderController {

    private final ScannerExt scannerExt;
    private OrderRepository orderRepository;

    public OrderController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.orderRepository = new OrderRepository(scannerExt);
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

    public void showOrderDetails(Order order) {
        int total = 0;
        orderRepository.findById(order.getId());
        List<Object[]> overview = orderRepository.overview(order.getId());
        for (Object[] objects : overview) {
            System.out.println("\n_-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_- ");
            System.out.print(" Emri : " + objects[0] );
            System.out.print(" Sasia : " + objects[1] );
            System.out.print(" Cmimi : " + objects[2] );
            System.out.println("\n_-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_-_-_-_-_-__-_-_-_-_-_-_-_-_-_-");
            total = (int) objects[2] + total;


        }
        System.out.println("Totali per tu paguar esht : " + total);
    }

}
