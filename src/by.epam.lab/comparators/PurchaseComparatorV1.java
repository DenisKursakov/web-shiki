package by.epam.lab.comparators;

import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV1 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase firstPurchase, Purchase secondPurchase) {
        int result;
        if (firstPurchase.getName().equals(secondPurchase.getName())) {
            if (secondPurchase instanceof PriceDiscountPurchase
                    && !(firstPurchase instanceof PriceDiscountPurchase)) {
                result = -1;
            } else if (firstPurchase instanceof PriceDiscountPurchase
                    && !(secondPurchase instanceof PriceDiscountPurchase)) {
                result = 1;
            } else {
                result = firstPurchase.getCost().compareTo(secondPurchase.getCost());
            }
        } else {
            result = firstPurchase.getName().compareTo(secondPurchase.getName());
        }
        return result;
    }
}
