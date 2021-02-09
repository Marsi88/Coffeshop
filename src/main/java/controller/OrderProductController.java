package controller;

import model.Order;
import model.OrderProduct;
import model.Product;
import repository.OrderProductRepository;
import repository.ProductRepository;
import util.ScannerExt;

public class OrderProductController {
    private final ScannerExt scannerExt;
    private OrderProductRepository orderProductRepository;
    private ProductRepository productRepository;

    public OrderProductController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.orderProductRepository = new OrderProductRepository(scannerExt);
        this.productRepository = new ProductRepository(scannerExt);
    }

    public void addOrderProduct(Order order, Product product) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrder(order);
        System.out.println("Sa sasi deshiron te marresh");
        Integer setQuantity = scannerExt.scanNumberField();
        orderProduct.setQuantity(setQuantity);
        orderProduct.setPrice(product.getSellPrice() * setQuantity);
        orderProductRepository.save(orderProduct);
        product.setQuantityStock(product.getQuantityStock() - setQuantity);
        productRepository.update(product);
    }

}
