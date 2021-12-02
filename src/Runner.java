import by.epam.lab.Byn;
import by.epam.lab.Purchase;
import by.epam.lab.PurchasesList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try {
            PurchasesList purchasesList = new PurchasesList("src/in.csv");

            for (Purchase purchase : purchasesList.getPurchases()) {
                System.out.println(purchase);
            }
        } catch (Exception e) {
            System.err.println("File is not found");
        }
    }
}
