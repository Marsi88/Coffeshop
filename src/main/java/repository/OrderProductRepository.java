package repository;

import model.Employee;
import model.OrderProduct;
import util.ScannerExt;

public class OrderProductRepository extends AbstractRepository<OrderProduct> {
    private final ScannerExt scannerExt;

    public OrderProductRepository(ScannerExt scannerExt) {
        this.aClass = OrderProduct.class;
        this.scannerExt = scannerExt;
    }

}
