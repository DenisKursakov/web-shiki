import by.epam.lab.*;

import java.util.Comparator;

public class Runner {
    public static void main( String[] args) {
        try {
            final String CSV_NAME = Constants.WAY_TO_FILES + args[Constants.ZERO]
                    + Constants.CSV_TYPE;
            final String ADDON_FILE_NAME = Constants.WAY_TO_FILES + args[Constants.ONE]
                    + Constants.CSV_TYPE;
            final String COMPARATOR_NAME = Constants.WAY_COMPARATOR + args[Constants.TWO];
            PurchasesList purchasesList = new PurchasesList(CSV_NAME);
            PurchasesList addonList = new PurchasesList(ADDON_FILE_NAME);
            Comparator comparator = (Comparator) Class.forName(COMPARATOR_NAME).newInstance();
            purchasesList.showPurchases();
            purchasesList.addPurchase(Constants.ZERO, addonList.getPurchases()
                    .get(addonList.getPurchases().size() - Constants.ONE));
            purchasesList.addPurchase(Constants.THOUSAND, addonList.getPurchases()
                    .get(Constants.ZERO));
            purchasesList.addPurchase(Constants.TWO, addonList.getPurchases().get(Constants.TWO));
            purchasesList.removePurchase(Constants.THREE);
            purchasesList.removePurchase(Constants.TEN);
            purchasesList.removePurchase(Constants.MINUS_FIVE);
            purchasesList.showPurchases();
            purchasesList.sortList(comparator);
            purchasesList.showPurchases();
            int indexSearch1 = purchasesList.searchPurchase(addonList.getPurchases()
                    .get(Constants.ONE),comparator);
            int indexSearch2 = purchasesList.searchPurchase(addonList.getPurchases()
                    .get(Constants.THREE),comparator);
            showSearchResult(indexSearch1, addonList, Constants.ONE);
            showSearchResult(indexSearch2, addonList, Constants.THREE);
        }catch (Exception e){
            System.err.println(Constants.EXCEPTION);
        }

    }
    private static void showSearchResult (int index, PurchasesList purchasesList, int indexByList){

        if (index > Constants.ZERO) {
            System.out.println(Constants.PURCHASE_FOUND + purchasesList.getPurchases()
                    .get(indexByList) + Constants.IS_FOUND + Constants.POSITION + index);
        } else {
            System.out.println(Constants.PURCHASE_FOUND +
                    purchasesList.getPurchases().get(indexByList) + Constants.IS_NOT_FOUND);
        }
    }

}
