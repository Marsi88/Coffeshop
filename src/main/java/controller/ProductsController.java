package controller;

import model.Employee;
import model.Product;
import model.ProductLine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.EmployeeRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsController {

    private final ScannerExt scannerExt;
    private EmployeeRepository employeeRepository;

    public ProductsController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.employeeRepository = new EmployeeRepository();
    }


    public void ManageProducts() {
        boolean back = true;
        while (back) {
            System.out.println
                    (" zgjidhni nje nga opsionet me poshte!" +
                            "\n1.Shto Produkt!" +
                            "\n2.Listo produktet!" +
                            "\n3.fshi produkte" +
                            "\n4. ndrysho te dhenat e produkteve" +
                            "\n5.Logout!");


            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));

            switch (choise) {

                case 1:
                    addProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    removeProduct();

                    break;
                case 4:
                    editPrduct();
                    break;
                case 5:
                    back = false;
                    break;
                default:
                    break;
            }
        }

    }

    public void addProduct() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Vendos Categorin e produktit e ri" +
                "\n1. Coffee - 2. Tea - 3. Cocoa - 4. Salep - 5. Sheqer ");
        Integer productCategoryName = this.scannerExt.scanNumberField();
        System.out.println("Vendosni emrin e produktit e ri");
        String productName = this.scannerExt.scanField();
        System.out.println("Sa kg per pako/thes do ket produkti ri?");
        Integer scaleProduct = this.scannerExt.scanNumberField();
        System.out.println("Vendos nje pershkrim te shkurter mbi produktin e ri");
        String descriptionProduct = this.scannerExt.scanField();
        System.out.println("Sa sasi do ka ne stok?");
        Integer quantityProduct = this.scannerExt.scanNumberField();
        System.out.println("Cfare cmim shitje do ket produkti ri");
        Integer sellPriceProduct = this.scannerExt.scanNumberField();
        ProductLine productLine = new ProductLine();
        Product product = new Product();
        productLine.setIdProductLines(productCategoryName);
        product.setProductLine(productLine);
        product.setName(productName);
        product.setProductDescription(descriptionProduct);
        product.setQuantityStock(quantityProduct);
        product.setSellPrice(sellPriceProduct);
        product.setScale(scaleProduct);
        product.setIsActive(1);
        session.save(product);
        transaction.commit();
        System.out.println("produkti u shtua");
        session.close();

    }

    public void listProducts() {
        boolean back = true;
        while (back) {
            System.out.println("Ke kategori deshiren te shikosh ?" +
                    "\n 1. COFFEE" + "\n 2. TEA" + "\n 3. COCOA" + "\n 4. SALEP" + "\n 5. SHEQER" + "\n 6. TE GJITHA" + "\n 7 BACK");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
            switch (choise) {

                case 1:
                    Session session = HibernateUtils.getSessionFactory().openSession();
                    Query query = session.createQuery
                            ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=1");
                    List<Object[]> seats = query.getResultList();
                    seats.forEach(seat -> {
                        System.out.print("  IdCategory : " + seat[0]);
                        System.out.print(" " + seat[1]);
                        System.out.println(" , ");
                    });

                    break;
                case 2:
                    session = HibernateUtils.getSessionFactory().openSession();
                    query = session.createQuery
                            ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=2");
                    List<Object[]> seats1 = query.getResultList();
                    seats1.forEach(seat -> {
                        System.out.print("  IdCategory : " + seat[0]);
                        System.out.print(" " + seat[1]);
                        System.out.println(" , ");
                    });

                    break;
                case 3:
                    session = HibernateUtils.getSessionFactory().openSession();
                    query = session.createQuery
                            ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=3");
                    List<Object[]> seats2 = query.getResultList();
                    seats2.forEach(seat -> {
                        System.out.print("  IdCategory : " + seat[0]);
                        System.out.print(" " + seat[1]);
                        System.out.println(" , ");
                    });

                    break;
                case 4:
                    session = HibernateUtils.getSessionFactory().openSession();
                    query = session.createQuery
                            ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=4");
                    List<Object[]> seats3 = query.getResultList();
                    seats3.forEach(seat -> {
                        System.out.print("  IdCategory : " + seat[0]);
                        System.out.print(" " + seat[1]);
                        System.out.println(" , ");
                    });
                case 5:
                    session = HibernateUtils.getSessionFactory().openSession();
                    query = session.createQuery
                            ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=5");
                    List<Object[]> seats4 = query.getResultList();
                    seats4.forEach(seat -> {
                        System.out.print("  IdCategory : " + seat[0]);
                        System.out.print(" " + seat[1]);
                        System.out.println(" , ");
                    });

                    break;

                case 6:
                    session = HibernateUtils.getSessionFactory().openSession();
                    query = session.createQuery
                            ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1");
                    List<Object[]> seats5 = query.getResultList();
                    seats5.forEach(seat -> {
                        System.out.print("  IdCategory : " + seat[0]);
                        System.out.print(" " + seat[1]);
                        System.out.println(" , ");
                    });

                    break;

                case 7:
                    back = false;
                    break;

                default:
                    break;
            }
        }
    }

    public void removeProduct() {
        listProduct();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Select an id to delete a product");
        Integer scanEmployeeId = scannerExt.scanNumberField();
        Product product = session.find(Product.class, scanEmployeeId);
        product.setIsActive(2);
        session.update(product);
        transaction.commit();
        session.close();

    }


    public void editPrduct() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        boolean back = true;
        while (back) {

            System.out.println("\nWhat you want to edit?" +
                    "\n1. Name" +
                    "\n2 selling price" +
                    "\n3 scale" +
                    "\n4 productDescription" +
                    "\n5 quantity" +
                    "\n6 Reactive a product" +
                    "\n7 back");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

            switch (choise) {
                case 1:
                    listProduct();
                    Transaction transaction = session.beginTransaction();
                    System.out.println("Select an id to edit a product");
                    Integer scanProductId = scannerExt.scanNumberField();
                    Product product = session.find(Product.class, scanProductId);
                    System.out.println("enter new name");
                    String editName = scannerExt.scanField();
                    product.setName(editName);
                    transaction.commit();
                    session.update(product);
                    System.out.println("Name changed Succesfully");
                    break;

                case 2:
                    listProduct();
                    transaction = session.beginTransaction();
                    System.out.println("Select an id to edit a product");
                    scanProductId = scannerExt.scanNumberField();
                    product = session.find(Product.class, scanProductId);
                    System.out.println("enter new price for the product");
                    Integer editSellingPrice = scannerExt.scanNumberField();
                    product.setSellPrice(editSellingPrice);
                    session.update(product);
                    transaction.commit();
                    break;
                case 3:
                    listProduct();
                    transaction = session.beginTransaction();
                    System.out.println("Select an id to edit a product");
                    scanProductId = scannerExt.scanNumberField();
                    product = session.find(Product.class, scanProductId);
                    System.out.println("enter new scale number  for the product per package");
                    Integer editScale = scannerExt.scanNumberField();
                    product.setScale(editScale);
                    session.update(product);
                    transaction.commit();

                    break;
                case 4:
                    listProduct();
                    transaction = session.beginTransaction();
                    System.out.println("Select an id to edit a product");
                    scanProductId = scannerExt.scanNumberField();
                    product = session.find(Product.class, scanProductId);
                    System.out.println("enter new description for the product");
                    String editDescription = scannerExt.scanField();
                    product.setProductDescription(editDescription);
                    session.update(product);
                    transaction.commit();


                    break;
                case 5:
                    listProduct();
                    transaction = session.beginTransaction();
                    System.out.println("Select an id to edit a product");
                    scanProductId = scannerExt.scanNumberField();
                    product = session.find(Product.class, scanProductId);
                    System.out.println("enter new quantity stock for the product");
                    Integer editQuantity = scannerExt.scanNumberField();
                    product.setQuantityStock(editQuantity);
                    session.update(product);
                    transaction.commit();
                    break;

                case 6:
                    listProduct();
                    session = HibernateUtils.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    System.out.println("Select an id to reactivate product");
                    Integer scanEmployeeId = scannerExt.scanNumberField();
                    Product product1 = session.find(Product.class, scanEmployeeId);
                    product1.setIsActive(1);
                    session.update(product1);
                    transaction.commit();
                    System.out.println("Product has been reactivated");

                    session.close();
                    break;

                case 7:
                    System.out.println("Loging out" +
                            "\n Goodbye");
                    back = false;
                    break;
                default:
                    session.close();
                    break;
            }
        }
    }


    private void listProduct() {
        System.out.println("Lista e produkteve !");
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select pl.id ,p.id,p.name,p.productDescription,p.quantityStock,p.scale,p.sellPrice,p.isActive from Product p left join p.productLine pl ");
        List<Object[]> seats = query.getResultList();
        seats.forEach(seat -> {
            System.out.print("IdCategory : " + seat[0]);
            System.out.print(" - ProductId :  " + seat[1]);
            System.out.print(" - name: " + seat[2]);
            System.out.print(" - Details: " + seat[3]);
            System.out.print(" - quantity: " + seat[4]);
            System.out.print(" - scale: " + seat[5]);
            System.out.print(" - selling: " + seat[6]);
            System.out.print(" - IsActive: " + seat[7]);
            System.out.println(" , ");

        });
    }

}