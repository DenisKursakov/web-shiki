package by.epam.lab;

import by.epam.lab.comparators.AbstractPurchaseComparator;
import by.epam.lab.comparators.PurchaseComparatorV1;

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
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            scanner.useDelimiter(Constants.SEMICOLON);
            purchases = new ArrayList<>();

            do {
                Purchase currentPurchase =
                        PurchaseFactory.getPurchaseFromFactory(scanner);
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
        if(index >= purchases.size()){
            purchases.add(purchase);
        } else if(index < Constants.ZERO) {
            purchases.add(0,purchase);
        } else {
            purchases.add(index,purchase);
        }
    }
    public void removePurchase(int index){
        if(index >= purchases.size() || index < Constants.ZERO){
            System.out.printf(Constants.INDEX_NOT_FOUND,index);
        } else {
            purchases.remove(index);
        }
    }
    public void sortList (Comparator<Purchase> comparator){

        Collections.sort(purchases, comparator);
    }
    public int searchPurchase (Purchase purchase, Comparator<Purchase> comparator){
       return Collections.binarySearch(purchases, purchase, comparator);
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
        System.out.println(Constants.FIRST_STRING_OF_TABLE);
        for (Purchase purchase : purchases) {
            String[] elements = purchase.toString().split(Constants.SEMICOLON);
            String discount = Constants.MINUS;
            if (elements.length == Constants.FIVE) {
                discount = elements[Constants.FOUR];
            }
            System.out.printf(Constants.FORMAT_TO_TABLE, elements[Constants.ZERO]
                    , elements[Constants.ONE], elements[Constants.TWO]
                    , discount, elements[Constants.THREE]);
        }
        System.out.printf(Constants.TABLE_TOTAL_COST_FORMAT, Constants.TOTAL_COST,getTotalCost());

    }
}
