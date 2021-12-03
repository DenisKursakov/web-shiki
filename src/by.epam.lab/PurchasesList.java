package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {
    private List<Purchase> purchases;
    private StringBuilder errorInfo = new StringBuilder();

    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(List<Purchase> purchases) {
        this.purchases = new ArrayList<>(purchases);
    }

    public PurchasesList(String csvName) {
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            scanner.useDelimiter(";");
            purchases = new ArrayList<>();

            do {
                Purchase currentPurchase =
                        PurchaseFactory.getPurchaseFromFactory(scanner, errorInfo);
                if (currentPurchase != null) {
                    purchases.add(currentPurchase);
                }
            } while (scanner.hasNextLine());

        } catch (FileNotFoundException | NoSuchElementException e) {
            System.err.println("File is not found");
        }
    }

    public StringBuilder getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(StringBuilder errorInfo) {
        this.errorInfo = errorInfo;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(int index, Purchase purchase) {
        if(index >= purchases.size() || index < 0){
            purchases.add(purchase);
        } else {
            purchases.add(index,purchase);
        }
    }
    public void removePurchase(int index){
        if(index >= purchases.size() || index < 0){
            System.err.printf("Index %d is not found\n",index);
        } else {
            purchases.remove(index);
        }
    }
    public void sortList (String comparatorName){
        switch (comparatorName){
            case "ComparatorV1":
                purchases.sort(Purchase::compareTo);
                break;
            case "ComparatorV2":
                purchases.sort(Purchase::compareTo2);
                break;
            default:
                System.err.println("Wrong comparator name");
        }

        purchases.sort(Purchase::compareTo2);
    }
    public int searchPurchase (Purchase purchase){
       return Collections.binarySearch(purchases, purchase, Purchase::compareTo);
    }

    public Byn getTotalCost() {
        Byn totalCost = new Byn();
        for (Purchase purchase : purchases) {
            if (purchase != null) {
                totalCost.add(purchase.getCost());
            }
        }
        return totalCost;
    }

    public void showPurchases() {
        final String FORMAT_TO_TABLE = "%s\t%10s%10s%10s%10s\n";
        final String DELIMITER = ";";
        final String MINUS = "-";
        final String TOTAL_COST = "Total cost = ";
        final String NAME = "Name";
        final String PRICE = "Price";
        final String DISCOUNT = "Discount";
        final String NUMBER = "Number";
        final String COST = "Cost";
        final String FIRST_STRING_OF_TABLE =
                String.format("%7s%10s%10s%10s%10s", NAME, PRICE, NUMBER, DISCOUNT, COST);
        System.out.println(FIRST_STRING_OF_TABLE);
        for (Purchase purchase : purchases) {
            String[] elements = purchase.toString().split(DELIMITER);
            String discount = MINUS;
            if (elements.length == 5) {
                discount = elements[4];
            }
            System.out.printf(FORMAT_TO_TABLE, elements[0], elements[1], elements[2],
                    discount, elements[3]);

        }

        System.out.println(TOTAL_COST + getTotalCost());

    }
}
