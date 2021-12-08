package by.epam.lab.comparators;

import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV1 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase firstPurchase, Purchase secondPurchase) {
        int result;
        int priorityFirstPurchase = priorityPurchase(firstPurchase);
        int prioritySecondPurchase = priorityPurchase(secondPurchase);
        if (firstPurchase.getName().equals(secondPurchase.getName())) {
            result = (priorityFirstPurchase == prioritySecondPurchase) ?
                    firstPurchase.getCost().compareTo(secondPurchase.getCost()) :
                    priorityFirstPurchase - prioritySecondPurchase;
        } else {
            result = firstPurchase.getName().compareTo(secondPurchase.getName());
        }
        return result;
    }

    private int priorityPurchase(Purchase purchase) {
        return purchase instanceof PriceDiscountPurchase ? 1 : 0;
    }
}
