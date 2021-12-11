package by.epam.lab.beans;

import by.epam.lab.Constants;


import java.util.Arrays;

public class PurchaseUtils {
    private final Purchase purchase;

    public PurchaseUtils() {
        this(null);
    }

    public PurchaseUtils(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(Constants.COST + purchase.getCost() + Constants.BYN);
    }

    public void printCostDiff(Purchase p) {
        String diffPurchase;
        int value = purchase.compareTo(p);
        Byn diffByn;
        if (value == 0) {
            diffPurchase = Constants.EMPTY_STRING;
            diffByn = new Byn(0);
        } else if (value > 0) {
            diffPurchase = Constants.POSITIVE_DIFF;
            diffByn = purchase.getCost().diff(p.getCost());
        } else {
            diffPurchase = Constants.NEGATIVE_DIFF;
            diffByn = p.getCost().diff(purchase.getCost());
        }
        System.out.println(diffPurchase + Constants.DIFF + Constants.EQUAL_SIGN
                + diffByn + Constants.BYN);
    }

    //    public void printIsSameCost(Purchase[] purchases) {
//        Arrays.sort(purchases);
//        boolean indexIsCorrect = Arrays.binarySearch(purchases, purchase) >= 0;
//        if (indexIsCorrect) {
//            System.out.println(Constants.PURCHASE_EXIST);
//        } else {
//            System.out.println(Constants.PURCHASE_DOES_NOT_EXIST);
//        }
//    }
    public void printIsSameCost(Purchase[] purchases) {
        boolean purchaseWasFound = false;
        for (Purchase p : purchases) {
            if (purchase.compareTo(p) == 0) {
                purchaseWasFound = true;
                break;
            }
        }
        if (purchaseWasFound) {
            System.out.println(Constants.PURCHASE_EXIST);
        } else {
            System.out.println(Constants.PURCHASE_DOES_NOT_EXIST);
        }
    }
}
