package repository;

import model.Employee;
import model.ProductLine;
import util.ScannerExt;

public class ProductLinesRepository extends AbstractRepository<ProductLine> {
    private final ScannerExt scannerExt;
    private ProductLinesRepository productLinesRepository;
    private ProductRepository productRepository;

    public ProductLinesRepository(ScannerExt scannerExt) {
        this.aClass = ProductLine.class;
        this.scannerExt = scannerExt;
    }

}
