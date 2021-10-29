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
            boolean equal = true;
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(scanner);
                Byn cost = purchases[i].getCost();
                if (maxCost.compareTo(cost) < 0) {
                    maxCost = cost;
                    purchaseWithMaxCost = purchases[i];
                }
                if (i > 0 && !purchases[i].equals(purchases[i - 1])) {
                    equal = false;
                }
                System.out.println(purchases[i]);
                if (i == purchases.length - 1) {
                    if (equal) {
                        System.out.println("Purchases are equal");
                    } else {
                        System.out.println("Purchases are not equal");
                    }
                    System.out.println("Purchase with max cost: " + purchaseWithMaxCost);
                }

            }


        } catch (FileNotFoundException e) {
            System.err.println("File is not found");
        }

    }
}
