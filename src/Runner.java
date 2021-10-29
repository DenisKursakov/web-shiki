import by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader("src/in.txt"))) {
            scanner.useLocale(Locale.ENGLISH);
            final int PURCHASES_NUMBER = 6;
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            Byn maxCost = new Byn();
            Purchase purchaseWithMaxCost = new Purchase();
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(scanner);
                Byn cost = purchases[i].getCost();
                if (maxCost.compareTo(cost) < 0) {
                    maxCost = cost;
                    purchaseWithMaxCost = purchases[i];
                }
                System.out.println(purchases[i]);
                if (i == purchases.length - 1) {
                    comparePurchases(purchases);
                    System.out.println("Purchase with max cost: " + purchaseWithMaxCost);
                }

            }


        } catch (FileNotFoundException e) {
            System.err.println("File is not found");
        }

    }

    private static void comparePurchases(Purchase[] purchases) {
        boolean equal = true;
        for (int j = purchases.length - 1; j > 0; j--) {
            if (!purchases[j].equals(purchases[j - 1])) {
                equal = false;
                break;
            }
        }
        if (equal) {
            System.out.println("Purchases are equal");
        } else {
            System.out.println("Purchases are not equal");
        }
    }
}
