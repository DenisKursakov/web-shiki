import by.epam.lab.*;
import by.epam.lab.beans.PurchasesList;
import by.epam.lab.comparators.PurchaseComparatorBuilder;
import by.epam.lab.exceptions.CsvLineException;

public class Runner {
    public static void main(String[] args) throws CsvLineException {
//        try {
            PurchaseComparatorBuilder.buildPurchaseComparator(args[0]);
            PurchasesList purchasesList = new PurchasesList(args[0]);
            PurchasesList addonList = new PurchasesList(args[1]);
            System.out.println(purchasesList.toTable());
            purchasesList.insert(0, addonList.getPurchases()
                    .get(addonList.getPurchases().size() - 1));
            purchasesList.insert(1000, addonList.getPurchases()
                    .get(0));
            purchasesList.insert(2, addonList.getPurchases().get(2));
            purchasesList.delete(3);
            purchasesList.delete(10);
            purchasesList.delete(-5);
            System.out.println(purchasesList.toTable());
            purchasesList.sortList(PurchaseComparatorBuilder.getPurchaseComparator());
            System.out.println(purchasesList.toTable());
            int indexSearch1 = purchasesList.searchPurchase(addonList.getPurchases()
                    .get(1), PurchaseComparatorBuilder.getPurchaseComparator());
            int indexSearch2 = purchasesList.searchPurchase(addonList.getPurchases()
                    .get(3), PurchaseComparatorBuilder.getPurchaseComparator());
            System.out.println(Constants.SEARCH_RESULT);
            showSearchResult(indexSearch1, addonList, 1);
            showSearchResult(indexSearch2, addonList, 3);
//        } catch (Exception e) {
//            System.err.println(Constants.EXCEPTION);
//        }

    }

    private static void showSearchResult(int index, PurchasesList purchasesList, int indexByList) throws CsvLineException {
        if (index >= 0) {
            System.out.println(Constants.PURCHASE_FOUND + purchasesList.getPurchases()
                    .get(indexByList) + Constants.IS_FOUND + Constants.POSITION + index);
        } else {
            System.out.println(Constants.PURCHASE_FOUND +
                    purchasesList.getPurchases().get(indexByList) + Constants.IS_NOT_FOUND);
        }
    }

}
