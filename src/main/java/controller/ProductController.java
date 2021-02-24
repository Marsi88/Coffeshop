package controller;

import model.Employee;
import model.Order;
import model.Product;
import model.ProductLine;
import repository.OrderProductRepository;
import repository.ProductLinesRepository;
import repository.ProductRepository;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductController {

    private final ScannerExt scannerExt;

    public ProductRepository productRepository;
    public ProductLinesRepository productLinesRepository;
    public Product product;
    private OrderProductRepository orderProductRepository;
    private OrderController orderController;
    private OrderProductController orderProductController;

    public ProductController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.productRepository = new ProductRepository(scannerExt);
        this.product = new Product();
        this.productLinesRepository = new ProductLinesRepository(scannerExt);
        this.orderProductRepository = new OrderProductRepository(scannerExt);
        this.orderController = new OrderController(scannerExt);
        this.orderProductController = new OrderProductController(scannerExt);
    }

    public void manageProducts() {
        boolean back = true;
        while (back) {
            System.out.println
                    (" zgjidhni nje nga opsionet me poshte!"
                            + "\n1.Shto Produkt!" +
                            "\n2.Listo produktet!" +
                            "\n3.Go back!");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3));
            switch (choise) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    back = false;
                    break;
                default:
                    break;
            }
        }

    }

    public void addProduct() {
        System.out.println("Vendos Categorin e produktit e ri");
        List<ProductLine> productLines = productLinesRepository.list();
        List<Integer> choises = new ArrayList<>();
        int index = 1;
        for (ProductLine pl : productLines) {
            choises.add(index);
            System.out.println(index + "." + pl.getDescription());
            index++;
        }
        System.out.println("Zgjidh nje nga kategorit qe deshironi me shtuar");
        Integer choise = scannerExt.scanRestrictedFieldNumber(choises);
        product.setProductLine(productLines.get(choise - 1));
        System.out.println("Vendosni emrin e produktit e ri");
        product.setName(this.scannerExt.scanField());
        System.out.println("Shto Menyren Matese per produktin e ri (Thes/Pako)");
        product.setScale(scannerExt.scanField());
        System.out.println("Vendos nje pershkrim te shkurter mbi produktin e ri");
        product.setProductDescription(scannerExt.scanField());
        System.out.println("Sa sasi do ka ne stok?");
        product.setQuantityStock(this.scannerExt.scanNumberField());
        System.out.println("Cfare cmim shitje do ket produkti ri");
        product.setSellPrice(scannerExt.scanNumberField());
        productRepository.save(product);
    }

    public void listProducts() {
        System.out.println("Shiko kategorit");
        List<ProductLine> productLines = productLinesRepository.list();
        List<Integer> choises = new ArrayList<>();
        int index = 1;
        for (ProductLine pl : productLines) {
            choises.add(index);
            System.out.println(index + "." + pl.getDescription());
            index++;
        }
        System.out.println("zgjidh nje nga kategorit per te shikuar produktet !");
        Integer productLineId = scannerExt.scanRestrictedFieldNumber(choises);
        List<Product> products = productRepository.listByProductLineId(productLineId);
        List<Integer> choises1 = new ArrayList<>();
        int index2 = 1;
        for (Product p : products) {
            choises1.add(index2);
            System.out.println(index2 + "." + p.getName());
            index2++;
        }
        System.out.println("Zgjidh nje nga produktet ose 0 per te shkuar mbrapa");
        choises1.add(0);
        Integer choise = scannerExt.scanRestrictedFieldNumber(choises1);
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
                Product product = products.get(choise - 1);
                Integer operationChoise = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1, 2, 3));
                switch (operationChoise) {
                    case 1:
                        System.out.println(product.printToConsole());
                        break;
                    case 2:
                        this.editProduct(product);
                        goback = false;
                        break;
                    case 3:
                        this.removeProduct(product);
                        goback = false;
                        break;
                    default:
                        goback = false;
                        break;
                }
            }
        }
    }

    public void editProduct(Product product) {
        boolean back = true;
        while (back) {
            boolean firstEdit = true;
            boolean isEditing = true;
            while (isEditing) {
                System.out.println("Zgjidh fushen" + (firstEdit ? "" : " tjeter") + " qe deshiron te ndryshosh" +
                        "\n 0.Shko Mbrapa" +
                        "\n 1.Emrin " +
                        "\n 2. Description" +
                        "\n 3.Cmimin" +
                        "\n 4.Quantity");
                Integer editChoise = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1, 2, 3, 4));

                switch (editChoise) {
                    case 1:
                        System.out.println("Vendosni vleren e re");
                        product.setName(scannerExt.scanField());
                        break;

                    case 2:
                        System.out.println("Vendosni vleren e re");
                        product.setProductDescription(scannerExt.scanField());

                        break;
                    case 3:
                        System.out.println("Vendosni vleren e re");
                        product.setSellPrice(scannerExt.scanNumberField());

                        break;
                    case 4:
                        System.out.println("Vendosni vleren e re");
                        product.setQuantityStock(scannerExt.scanNumberField());
                        break;

                    default:
                        isEditing = false;
                        back = false;
                        break;

                }
                firstEdit = false;
            }
            productRepository.update(product);
        }
    }

    public void removeProduct(Product product) {
        productRepository.delete(product);
        System.out.println("Produkti u fshi me sukses");
    }

    public void selectProduct(Order order) {
        boolean isEditing = true;
        while (isEditing) {
            System.out.println("Shiko kategorit");
            List<ProductLine> productLines = productLinesRepository.list();
            List<Integer> choises = new ArrayList<>();
            int index = 1;
            for (ProductLine pl : productLines) {
                choises.add(index);
                System.out.println(index + "." + pl.getDescription());
                index++;
            }

            System.out.println("zgjidh nje nga kategorit per te shikuar produktet !");
            Integer productLineId = scannerExt.scanRestrictedFieldNumber(choises);
            List<Product> products = productRepository.listByProductLineId(productLineId);
            List<Integer> choises1 = new ArrayList<>();
            int index2 = 1;
            for (Product p : products) {
                choises1.add(index2);
                System.out.println(index2 + "." + p.getName());
                index2++;
            }
            System.out.println("Zgjidh nje nga produktet ose 0 per te shkuar mbrapa");
            choises1.add(0);
            Integer choise = scannerExt.scanRestrictedFieldNumber(choises1);
            if (choise == 0) {
                return;
            } else {
                boolean goback = true;
                while (goback) {
                    System.out.println("Zgjidh nje nga opsionet" +
                            "\n0. Shko Mbrapa" +
                            "\n1.Shto ne shport");
                    Product product = products.get(choise - 1);
                    Integer operationChoise = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1));
                    switch (operationChoise) {
                        case 1:
                            orderProductController.addOrderProduct(order, product);
                            System.out.println("Deshiron te shtosh dicka tjeter ne porosin " +
                                    "\nPO - JO");
                            String addMoreToCart = scannerExt.scanField();
                            if ("PO".equalsIgnoreCase(addMoreToCart))
                                isEditing = true;
                            else
                                isEditing = false;
                            System.out.println("Porosia u shtua me sukses");
                            orderController.showOrderDetails(order);
                            goback = false;
                            break;
                        default:
                            goback = false;
                            break;
                    }
                }
            }
        }

    }
}