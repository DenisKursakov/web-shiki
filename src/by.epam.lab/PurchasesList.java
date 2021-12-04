package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {
    private List<Purchase> purchases;

    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(List<Purchase> purchases) {
        this.purchases = new ArrayList<>(purchases);
    }

    public PurchasesList(String csvName) {
        final String CSV_NAME = Constants.WAY_TO_FILES + csvName
                + Constants.CSV_TYPE;
        try (Scanner scanner = new Scanner(new FileReader(CSV_NAME))) {
            scanner.useLocale(Locale.ENGLISH);
            scanner.useDelimiter(Constants.SEMICOLON);
            purchases = new ArrayList<>();
            do {
                Purchase currentPurchase =
                        PurchaseFactory.getPurchaseFromFactory(scanner.nextLine());
                if (currentPurchase != null) {
                    purchases.add(currentPurchase);
                }
            } while (scanner.hasNextLine());

        } catch (FileNotFoundException | NoSuchElementException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
        }
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(int index, Purchase purchase) {
        if (index >= purchases.size()) {
            purchases.add(purchase);
        } else if (index < 0) {
            purchases.add(0, purchase);
        } else {
            purchases.add(index, purchase);
        }
    }

    public void removePurchase(int index) {
        if (index >= purchases.size() || index < 0) {
            System.out.printf(Constants.INDEX_NOT_FOUND, index);
        } else {
            purchases.remove(index);
        }
    }

    public void sortList(Comparator<Purchase> comparator) {
        Collections.sort(purchases, comparator);
    }

    public int searchPurchase(Purchase purchase, Comparator<Purchase> comparator) {
        return Collections.binarySearch(purchases, purchase, comparator);
    }

    public Byn getTotalCost() {
        Byn totalCost = new Byn();
        for (Purchase purchase : purchases) {
            totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public void showPurchases() {
        System.out.println(Constants.FIRST_STRING_OF_TABLE);
        for (Purchase purchase : purchases) {
            String[] elements = purchase.toString().split(Constants.SEMICOLON);
            String discount = Constants.MINUS;
            if (elements.length == Constants.DISCOUNT_PURCHASE_LENGTH) {
                discount = elements[Constants.IN_LINE_DISCOUNT];
            }
            System.out.printf(Constants.FORMAT_TO_TABLE, elements[Constants.IN_LINE_NAME]
                    , elements[Constants.IN_LINE_PRICE], elements[Constants.IN_LINE_NUMBER]
                    , discount, elements[elements.length - 1]);
        }
        System.out.printf(Constants.TABLE_TOTAL_COST_FORMAT, Constants.TOTAL_COST, getTotalCost());

    }
}
