package by.epam.lab.comparators;

import by.epam.lab.PriceDiscountPurchase;
import by.epam.lab.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV2 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase firstPurchase, Purchase secondPurchase) {
        int result;
        if (firstPurchase.getName().equals(secondPurchase.getName())) {
            if (secondPurchase.getClass() == PriceDiscountPurchase.class &&
                    firstPurchase.getClass() == Purchase.class) {
                result = -1;
            } else if (firstPurchase.getClass() == PriceDiscountPurchase.class &&
                    secondPurchase.getClass() == Purchase.class) {
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
