package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PurchasesList {
    private List<Purchase> purchases;

    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(List<Purchase> purchases) {
        this.purchases = new ArrayList<>(purchases);
    }

    public PurchasesList(String csvName) {
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            scanner.useDelimiter("\\n,[;]");
            purchases = new ArrayList<>();
            do {
                purchases.add(PurchaseFactory.getPurchaseFromFactory(scanner));
                System.out.println(PurchaseFactory.getPurchaseFromFactory(scanner));
            } while (scanner.hasNext());
        } catch (FileNotFoundException e) {
            System.err.println("File is not found");
        }
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
