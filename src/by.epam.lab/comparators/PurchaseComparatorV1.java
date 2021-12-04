package by.epam.lab.comparators;

import by.epam.lab.Constants;
import by.epam.lab.PriceDiscountPurchase;
import by.epam.lab.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV1 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase firstPurchase, Purchase secondPurchase) {
        if(firstPurchase.getName().equals(secondPurchase.getName())){
            if(secondPurchase instanceof PriceDiscountPurchase
                    && !(firstPurchase instanceof PriceDiscountPurchase)){
                return Constants.MINUS_ONE;
            }
            if(firstPurchase instanceof PriceDiscountPurchase
                    && !(secondPurchase instanceof PriceDiscountPurchase)){
                return Constants.ONE;
            }
            return firstPurchase.getCost().compareTo(secondPurchase.getCost());
        }
        return firstPurchase.getName().compareTo(secondPurchase.getName());
    }
}
