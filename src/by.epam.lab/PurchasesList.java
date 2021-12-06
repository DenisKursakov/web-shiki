package by.epam.lab;
import by.epam.lab.exceptions.CsvLineException;
import by.epam.lab.exceptions.NonPositiveArgumentException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PurchasesList {
    private List<Purchase> purchases;
    private boolean purchaseIsSorted = false;
    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(String csvName) {
        final String CSV_NAME = Constants.WAY_TO_FILES + csvName
                + Constants.CSV_TYPE;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(CSV_NAME));
            scanner.useLocale(Locale.ENGLISH);
            scanner.useDelimiter(Constants.SEMICOLON);
            purchases = new ArrayList<>();
            while (scanner.hasNextLine()) {
                try {
                    purchases.add(PurchaseFactory.getPurchaseFromFactory(scanner.nextLine()));
                } catch (CsvLineException | NonPositiveArgumentException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            purchases = new ArrayList<>();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public List<Purchase> getPurchases() {
        List<Purchase> purchasesClone = new ArrayList<>();
        for (Purchase purchase: purchases) {
            if(purchase.getClass() == Purchase.class){
               purchasesClone.add(new Purchase(purchase.getName(),
                       purchase.getPrice(),purchase.getNumberOfUnits()));
            } else {
                purchasesClone.add(new PriceDiscountPurchase(
                        purchase.getName(),purchase.getPrice(),
                        purchase.getNumberOfUnits(),((PriceDiscountPurchase) purchase)
                        .getDiscount()));
            }
        }
        return purchasesClone;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public boolean isIndexCorrect (int index){
        return index < purchases.size() && index > 0;
    }
    public void insert(int index, Purchase purchase) {
        if (index >= purchases.size()) {
            purchases.add(purchase);
        } else if (index < 0) {
            purchases.add(0, purchase);
        } else {
            purchases.add(index, purchase);
        }
        purchaseIsSorted = false;
    }

    public void delete(int index) {
         if(isIndexCorrect(index)) {
            purchases.remove(index);
            purchaseIsSorted = false;
        }
    }

    public void sortList(Comparator<Purchase> comparator) {
        Collections.sort(purchases, comparator);
        purchaseIsSorted = true;
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
    public String toTable() {
        StringBuilder info = new StringBuilder(Constants.FIRST_STRING_OF_TABLE);
        for (Purchase purchase : purchases) {
            info.append(purchase.lineToTableFormat());
        }
        info.append(String.format(Constants.TABLE_TOTAL_COST_FORMAT,
                Constants.TOTAL_COST, getTotalCost()));
        return info.toString();
    }
}
