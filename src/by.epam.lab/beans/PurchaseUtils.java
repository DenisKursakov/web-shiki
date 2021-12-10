package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.Converter;


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
        if (value == 0) {
            diffPurchase = Constants.EMPTY_STRING;
        } else if (value > 0) {
            diffPurchase = Constants.POSITIVE_DIFF;
        } else {
            diffPurchase = Constants.NEGATIVE_DIFF;
            value *= -1;
        }
        System.out.println(diffPurchase + Constants.DIFF + Constants.EQUAL_SIGN
                + Converter.convert(value) + Constants.BYN);
    }

    public void printIsSameCost(Purchase[] purchases) {
        Arrays.sort(purchases);
        int index = Arrays.binarySearch(purchases, purchase);
        if (index >= 0) {
            System.out.println(Constants.PURCHASE_EXIST);
        } else {
            System.out.println(Constants.PURCHASE_DOES_NOT_EXIST);
        }
    }
}
