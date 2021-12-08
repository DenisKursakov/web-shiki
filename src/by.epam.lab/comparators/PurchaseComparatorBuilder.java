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
        final String COMPARATOR_NAME = Constants.WAY_COMPARATOR +
                comparatorName + Constants.CSV_TYPE;
        try {
            purchaseComparator =
                    (Comparator<Purchase>) Class.forName(COMPARATOR_NAME).newInstance();
        } catch (Exception e) {
            purchaseComparator = new PurchaseComparatorV1();
        }
    }
}

