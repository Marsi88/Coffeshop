package controller;

import model.Employee;
import model.Product;
import model.ProductLine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.EmployeeRepository;
import repository.ProductRepository;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsController {

    private final ScannerExt scannerExt;

    private ProductRepository productRepository;

    public ProductsController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.productRepository = new ProductRepository();
    }


    public void ManageProducts() {
        boolean back = true;
        while (back) {
            System.out.println
                    (" zgjidhni nje nga opsionet me poshte!" + "\n1.Shto Produkt!" + "\n2.Listo produktet!" + "\n3.fshi produkte" + "\n4. ndrysho te dhenat e produkteve" + "\n5.Logout!");


            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5));

            back = manageProductsSwitch(back, choise);
        }

    }

    private boolean manageProductsSwitch(boolean back, Integer choise) {
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
        return back;
    }

    public void addProduct() {


        addNewProduct();

    }

    private void addNewProduct() {
        System.out.println("Vendos Categorin e produktit e ri" +
                "\n1. Coffee - 2. Tea - 3. Cocoa - 4. Salep - 5. Sheqer ");
        Integer productCategoryName = this.scannerExt.scanNumberField();
        System.out.println("Vendosni emrin e produktit e ri");
        String productName = this.scannerExt.scanField();
        System.out.println("Sa kg per pako/thes do ket produkti ri?");
        String scaleProduct = this.scannerExt.scanField();
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
        productRepository.addProduct(product);
    }

    public void listProducts() {
        boolean back = true;
        while (back) {
            System.out.println("Ke kategori deshiren te shikosh ?" +
                    "\n 1. COFFEE" + "\n 2. TEA" + "\n 3. COCOA" + "\n 4. SALEP" + "\n 5. SHEQER" + "\n 6. TE GJITHA" + "\n 7 BACK");
            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
            back = listProductsSwitch(back, choise);
        }
    }

    private boolean listProductsSwitch(boolean back, Integer choise) {
        switch (choise) {

            case 1:
                productRepository.listCoffee();

                break;
            case 2:
                productRepository.listTea();
                break;
            case 3:
                List<Object[]> listCocoa = productRepository.listCocoa();
                listCocoa.forEach(name -> {
                    System.out.print("  IdCategory : " + name[0]);
                    System.out.print(" " + name[1]);
                    System.out.println(" , ");
                });
                break;
            case 4:
                List<Object[]> listSalep = productRepository.listSalep();
                listSalep.forEach(seat -> {
                    System.out.print("  IdCategory : " + seat[0]);
                    System.out.print(" " + seat[1]);
                    System.out.println(" , ");
                });
            case 5:
                List<Object[]> listSheqer = productRepository.listSheqer();
                listSheqer.forEach(seat -> {
                    System.out.print("  IdCategory : " + seat[0]);
                    System.out.print(" " + seat[1]);
                    System.out.println(" , ");
                });

                break;

            case 6:
                List<Object[]> listAll = productRepository.listAll();
                listAll.forEach(seat -> {
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
        return back;
    }

    public void removeProduct() {
        listProduct();
        productRepository.removeProduct(scannerExt);

    }


    public void editPrduct() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        boolean back = true;
        while (back) {

            System.out.println("\nWhat you want to edit?" + "\n1. Name" + "\n2 selling price" + "\n3 scale" + "\n4 productDescription" + "\n5 quantity" + "\n6 Reactive a product" + "\n7 back");

            Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

            back = editProductSwitch(session, back, choise);
        }
    }

    private boolean editProductSwitch(Session session, boolean back, Integer choise) {
        switch (choise) {
            case 1:
                listProduct();
                productRepository.editName(scannerExt);
                break;

            case 2:
                listProduct();
                productRepository.editPrice(scannerExt);
                break;
            case 3:
                listProduct();
                productRepository.editScale(scannerExt);

                break;
            case 4:
                listProduct();
                productRepository.editDescription(scannerExt);


                break;
            case 5:
                listProduct();
                productRepository.editQuantity(scannerExt);
                break;

            case 6:
                listProduct();
                productRepository.reactivateProduct(scannerExt);
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
        return back;
    }


    private void listProduct() {
        List<Object[]> listProductsForEdit = productRepository.listProductsForEdit();
        listProductsForEdit.forEach(seat -> {
            soutListProductForEdit(seat);

        });
    }

    private void soutListProductForEdit(Object[] seat) {
        System.out.print("IdCategory : " + seat[0]);
        System.out.print(" - ProductId :  " + seat[1]);
        System.out.print(" - name: " + seat[2]);
        System.out.print(" - Details: " + seat[3]);
        System.out.print(" - quantity: " + seat[4]);
        System.out.print(" - scale: " + seat[5]);
        System.out.print(" - selling: " + seat[6]);
        System.out.print(" - IsActive: " + seat[7]);
        System.out.println(" , ");
    }

}