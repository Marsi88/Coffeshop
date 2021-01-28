import controller.EmployeeController;
import util.HibernateUtils;
import util.ScannerExt;

import java.util.Arrays;
import java.util.Scanner;

import static repository.Colors.*;

public class CoffeeShopApplication {

    public static void main(String[] args) {
        //init hibernate
        HibernateUtils.getSessionFactory();

        ScannerExt scannerExt = new ScannerExt(new Scanner(System.in));

        boolean quit = true;

        while (quit) {
            System.out.println(ANSI_YELLOW + "Miresevini ne CoffeeShop Magazine");
            System.out.println(ANSI_CYAN + "Zgjidhni nje nga opsionet me poshte!");
            System.out.println(ANSI_GREEN + "1.Login");
            System.out.println(ANSI_RED + "0.Quit");

            Integer choice = scannerExt.scanRestrictedFieldNumber(Arrays.asList(0, 1));

            switch (choice) {
                case 0:
                    quit = false;
                    break;
                case 1:
                    EmployeeController employeeController = new EmployeeController(scannerExt);
                    employeeController.login();
                    break;
                default:
                    break;
            }
        }

        scannerExt.close();
        System.exit(0);
    }
}
