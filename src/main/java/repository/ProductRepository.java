package repository;

import controller.ProductsController;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.List;

public class ProductRepository {


    public void addProduct(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        System.out.println("produkti u shtua");
        session.close();
    }


    public void listCoffee() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=1");
        List<Object[]> seats = query.getResultList();
        seats.forEach(seat -> {
            System.out.print("  IdCategory : " + seat[0]);
            System.out.print(" " + seat[1]);
            System.out.println(" , ");
        });
        session.close();

    }


    public void listTea() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=2");
        List<Object[]> seats1 = query.getResultList();
        seats1.forEach(seat -> {
            System.out.print("  IdCategory : " + seat[0]);
            System.out.print(" " + seat[1]);
            System.out.println(" , ");
        });
        session.close();

    }

    public List<Object[]> listCocoa() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=3");
        List<Object[]> listCocoa = query.getResultList();

        session.close();
        return listCocoa;
    }

    public List<Object[]> listSalep() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=4");
        List<Object[]> listSalep = query.getResultList();
        session.close();

        return listSalep;
    }

    public List<Object[]> listSheqer() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1 and pl.id=5");
        List<Object[]> listSheqer = query.getResultList();
        return listSheqer;
    }

    public List<Object[]> listAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery
                ("select pl.id,p from Product p left join p.productLine pl where p.isActive=1");
        List<Object[]> listAll = query.getResultList();
        return listAll;
    }


    public List<Object[]> listProductsForEdit() {
        System.out.println("Lista e produkteve !");
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query query = session.createQuery("select pl.id ,p.id,p.name,p.productDescription,p.quantityStock,p.scale,p.sellPrice,p.isActive from Product p left join p.productLine pl ");
        List<Object[]> listProductsForEdit = query.getResultList();
        return listProductsForEdit;
    }

    public void removeProduct(ScannerExt scannerExt) {
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

    public void editName(ScannerExt scannerExt) {
        Session session=HibernateUtils.getSessionFactory().openSession();
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
        session.close();
    }

    public void editPrice(ScannerExt scannerExt) {
        Session session=HibernateUtils.getSessionFactory().openSession();
       Transaction transaction = session.beginTransaction();
        System.out.println("Select an id to edit a product");
      Integer  scanProductId = scannerExt.scanNumberField();
     Product  product = session.find(Product.class, scanProductId);
        System.out.println("enter new price for the product");
        Integer editSellingPrice = scannerExt.scanNumberField();
        product.setSellPrice(editSellingPrice);
        session.update(product);
        transaction.commit();
        session.close();
    }

    public void editScale(ScannerExt scannerExt) {
        Session session=HibernateUtils.getSessionFactory().openSession();
       Transaction transaction = session.beginTransaction();
        System.out.println("Select an id to edit a product");
       Integer scanProductId = scannerExt.scanNumberField();
     Product   product = session.find(Product.class, scanProductId);
        System.out.println("enter new scale number  for the product per package");
        String editScale = scannerExt.scanField();
        product.setScale(editScale);
        session.update(product);
        transaction.commit();
        System.out.println("Scale edited successfully");
        session.close();
    }

    public void editDescription(ScannerExt scannerExt) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction  transaction = session.beginTransaction();
        System.out.println("Select an id to edit a product");
        Integer    scanProductId = scannerExt.scanNumberField();
        Product  product = session.find(Product.class, scanProductId);
        System.out.println("enter new description for the product");
        String editDescription = scannerExt.scanField();
        product.setProductDescription(editDescription);
        session.update(product);
        System.out.println("description edited successfully");
        transaction.commit();
        session.close();


    }

    public void editQuantity(ScannerExt scannerExt) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction   transaction = session.beginTransaction();
        System.out.println("Select an id to edit a product");
        Integer   scanProductId = scannerExt.scanNumberField();
        Product   product = session.find(Product.class, scanProductId);
        System.out.println("enter new quantity stock for the product");
        Integer editQuantity = scannerExt.scanNumberField();
        product.setQuantityStock(editQuantity);
        session.update(product);
        System.out.println("Quantity edited successfully");
        transaction.commit();
        session.close();
    }

    public void reactivateProduct(ScannerExt scannerExt) {
       Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction transaction = session.beginTransaction();
        System.out.println("Select an id to reactivate product");
        Integer scanEmployeeId = scannerExt.scanNumberField();
        Product product1 = session.find(Product.class, scanEmployeeId);
        product1.setIsActive(1);
        session.update(product1);
        transaction.commit();
        System.out.println("Product has been reactivated");

        session.close();
    }
}


