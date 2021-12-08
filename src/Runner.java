import by.epam.lab.*;
import by.epam.lab.beans.PurchasesList;
import by.epam.lab.comparators.PurchaseComparatorBuilder;


public class Runner {
    public static void main(String[] args) {
        try {
            PurchaseComparatorBuilder.buildPurchaseComparator(args[0]);
            PurchasesList purchasesList = new PurchasesList(args[0]);
            PurchasesList addonList = new PurchasesList(args[1]);
            showPurchasesList(purchasesList);
            purchasesList.insert(0, addonList.getPurchases()
                    .get(addonList.getPurchases().size() - 1));
            purchasesList.insert(1000, addonList.getPurchases()
                    .get(0));
            purchasesList.insert(2, addonList.getPurchases().get(2));
            delete(purchasesList, 3);
            delete(purchasesList, 10);
            delete(purchasesList, -5);
            showPurchasesList(purchasesList);
            purchasesList.sortList();
            showPurchasesList(purchasesList);
            int indexSearch1 = purchasesList.searchPurchase(addonList.getPurchases().get(1));
            int indexSearch2 = purchasesList.searchPurchase(addonList.getPurchases().get(3));
            System.out.println(Constants.SEARCH_RESULT);
            showSearchResult(indexSearch1, addonList, 1);
            showSearchResult(indexSearch2, addonList, 3);
        } catch (Exception e) {
            System.err.println(Constants.EXCEPTION);
        }

    }

    private static void showSearchResult(int index, PurchasesList purchasesList, int indexByList) {
        if (index >= 0) {
            System.out.println(Constants.PURCHASE_FOUND + purchasesList.getPurchases()
                    .get(indexByList) + Constants.IS_FOUND + Constants.POSITION + index);
        } else {
            System.out.println(Constants.PURCHASE_FOUND +
                    purchasesList.getPurchases().get(indexByList) + Constants.IS_NOT_FOUND);
        }
    }

    private static void showPurchasesList(PurchasesList purchasesList) {
        System.out.println(purchasesList.toTable());
    }

    private static void delete(PurchasesList purchasesList, int index) {
        if(purchasesList.isIndexCorrect(index)) {
            purchasesList.delete(index);
            System.out.println(Constants.INDEX + index + Constants.WAS_FOUND_AND_DELETED);
        } else {
            System.out.println(Constants.INDEX + index + Constants.IS_NOT_FOUND);
        }

    }

}
