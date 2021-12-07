package by.epam.lab.comparators;

import by.epam.lab.Constants;
import by.epam.lab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorBuilder {
    private static Comparator<Purchase> purchaseComparator;

    private PurchaseComparatorBuilder() {
    }

    public static Comparator<Purchase> getPurchaseComparator() {
        return purchaseComparator;
    }

    public static void buildPurchaseComparator(String comparatorName) {
        if (purchaseComparator != null) {
            return;
        }
        final String COMPARATOR_NAME = Constants.WAY_COMPARATOR + comparatorName;
        try {
           purchaseComparator = (Comparator<Purchase>) Class.forName(comparatorName).newInstance();
        } catch (Exception e) {
            //use default class independently on exception
            purchaseComparator = new PurchaseComparatorV1();
        }
    }
}

