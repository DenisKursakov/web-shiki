package by.epam.lab.comparators;

import by.epam.lab.Constants;
import by.epam.lab.PriceDiscountPurchase;
import by.epam.lab.Purchase;

import java.util.Comparator;

public class PurchaseComparatorV2 implements Comparator<Purchase> {
    @Override
    public int compare(Purchase firstPurchase, Purchase secondPurchase) {
        if(firstPurchase.getName().equals(secondPurchase.getName())){
            if(secondPurchase.getClass() == firstPurchase.getClass()){
                return firstPurchase.getCost().compareTo(secondPurchase.getCost());
            }
            if(secondPurchase.getClass() == PriceDiscountPurchase.class){
                return Constants.MINUS_ONE;
            } else {
                return Constants.ONE;
            }
        }
        return firstPurchase.getName().compareTo(secondPurchase.getName());
    }
}
