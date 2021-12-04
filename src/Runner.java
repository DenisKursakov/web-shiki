import by.epam.lab.*;
import by.epam.lab.comparators.PurchaseComparatorV1;

import java.util.Comparator;

public class Runner {
    public static void main(String[] args) {
        try {
            final String CSV_NAME = Constants.WAY_TO_FILES + args[0]
                    + Constants.CSV_TYPE;
            final String ADDON_FILE_NAME = Constants.WAY_TO_FILES + args[1]
                    + Constants.CSV_TYPE;
            final String COMPARATOR_NAME = Constants.WAY_COMPARATOR + args[2];
            PurchasesList purchasesList = new PurchasesList(CSV_NAME);
            PurchasesList addonList = new PurchasesList(ADDON_FILE_NAME);
            Comparator<Purchase> comparator =
                    (Comparator<Purchase>) Class.forName(COMPARATOR_NAME).newInstance();
            purchasesList.showPurchases();
            purchasesList.addPurchase(0, addonList.getPurchases()
                    .get(addonList.getPurchases().size() - 1));
            purchasesList.addPurchase(1000, addonList.getPurchases()
                    .get(0));
            purchasesList.addPurchase(2, addonList.getPurchases().get(2));
            purchasesList.removePurchase(3);
            purchasesList.removePurchase(10);
            purchasesList.removePurchase(-5);
            purchasesList.showPurchases();
            purchasesList.sortList(comparator);
            purchasesList.showPurchases();
            int indexSearch1 = purchasesList.searchPurchase(addonList.getPurchases()
                    .get(1), comparator);
            int indexSearch2 = purchasesList.searchPurchase(addonList.getPurchases()
                    .get(3), comparator);
            showSearchResult(indexSearch1, addonList, 1);
            showSearchResult(indexSearch2, addonList, 3);
        } catch (Exception e) {
            System.err.println(Constants.EXCEPTION);
        }

    }

    private static void showSearchResult(int index, PurchasesList purchasesList, int indexByList) {

        if (index > Constants.ZERO) {
            System.out.println(Constants.PURCHASE_FOUND + purchasesList.getPurchases()
                    .get(indexByList) + Constants.IS_FOUND + Constants.POSITION + index);
        } else {
            System.out.println(Constants.PURCHASE_FOUND +
                    purchasesList.getPurchases().get(indexByList) + Constants.IS_NOT_FOUND);
        }
    }

}
