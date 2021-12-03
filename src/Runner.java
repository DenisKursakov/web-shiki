import by.epam.lab.Byn;
import by.epam.lab.PriceDiscountPurchase;
import by.epam.lab.Purchase;
import by.epam.lab.PurchasesList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        final String EXCEPTION = "some exception was caught";
        final String PURCHASE_FOUND = "Purchase: ";
        final String POSITION = " at position ";
        final String IS_FOUND = " is found";
        final String IS_NOT_FOUND = " is not found";
        final String SRC = "src/";
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter file name");
            final String CSV_NAME = SRC + scanner.nextLine();
            System.out.println("Enter additional file name");
            final String ADDON_FILE_NAME = SRC + scanner.nextLine();
            System.out.println("Enter comparator version name"); //ComparatorV1 or ComparatorV2
            final String COMPARATOR_NAME = scanner.nextLine();
            PurchasesList purchasesList = new PurchasesList(CSV_NAME);
            PurchasesList addonList = new PurchasesList(ADDON_FILE_NAME);
            System.err.println(purchasesList.getErrorInfo().toString() +
                    addonList.getErrorInfo().toString());
            purchasesList.showPurchases();
            purchasesList.addPurchase(0, addonList.getPurchases()
                    .get(addonList.getPurchases().size() - 1));
            purchasesList.addPurchase(1000, addonList.getPurchases().get(0));
            purchasesList.addPurchase(2, addonList.getPurchases().get(2));
            purchasesList.removePurchase(3);
            purchasesList.removePurchase(10);
            purchasesList.removePurchase(-5);
            purchasesList.showPurchases();
            purchasesList.sortList(COMPARATOR_NAME);
            purchasesList.showPurchases();
            int indexSearch1 = purchasesList.searchPurchase(addonList.getPurchases().get(1));
            int indexSearch2 = purchasesList.searchPurchase(addonList.getPurchases().get(3));
            if (indexSearch1 > 0) {
                System.out.println(PURCHASE_FOUND + addonList.getPurchases().get(1) +
                        IS_FOUND + POSITION + indexSearch1);
            } else {
                System.out.println(PURCHASE_FOUND + addonList.getPurchases().get(1) + IS_NOT_FOUND);
            }
            if (indexSearch2 > 0) {
                System.out.println(PURCHASE_FOUND + addonList.getPurchases().get(3) +
                        IS_FOUND + POSITION + indexSearch2);
            } else {
                System.out.println(PURCHASE_FOUND + addonList.getPurchases().get(3) + IS_NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println(EXCEPTION);
        }

    }

}
