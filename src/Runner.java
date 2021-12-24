
import by.epam.lab.Constants;
import by.epam.lab.interfaces.EntryChecker;
import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.beans.Purchase;
import by.epam.lab.enums.WeekDay;
import by.epam.lab.factories.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(Constants.IN_FILE_WAY_STR))) {
            scanner.useLocale(Locale.ENGLISH);
            Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
            Map<Purchase, WeekDay> lastPurchaseMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> dayPurchaseMap = new EnumMap<>(WeekDay.class);
            List<PriceDiscountPurchase> pricePurchases = new ArrayList<>();
            while (scanner.hasNextLine()) {
                Purchase currentPurchase = PurchasesFactory.getPurchaseFromFactory(scanner);
                WeekDay currentPurchaseDay = WeekDay.valueOf(scanner.nextLine());
                lastPurchaseMap.put(currentPurchase, currentPurchaseDay);
                if (!firstPurchaseMap.containsKey(currentPurchase)) {
                    firstPurchaseMap.put(currentPurchase, currentPurchaseDay);
                }
                List<Purchase> valueListPurchase = dayPurchaseMap.get(currentPurchaseDay);
                if (valueListPurchase == null) {
                    dayPurchaseMap.put(currentPurchaseDay, valueListPurchase = new ArrayList<>());
                }
                valueListPurchase.add(currentPurchase);
                if (currentPurchase.getClass() == PriceDiscountPurchase.class) {
                    pricePurchases.add((PriceDiscountPurchase) currentPurchase);
                }

            }
            printMap(lastPurchaseMap, Constants.LAST_PURCHASE_MAP);
            printMap(firstPurchaseMap, Constants.FIRST_PURCHASE_MAP);
            Purchase purchaseForSearch = new Purchase(Constants.ELEMENT_BREAD,
                    new Byn(155), 0);
            findAndShow(firstPurchaseMap, purchaseForSearch,
                    Constants.FIRST_DAY_ELEMENT_INFO + purchaseForSearch.getPrice());
            findAndShow(lastPurchaseMap, purchaseForSearch,
                    Constants.LAST_DAY_ELEMENT_INFO + purchaseForSearch.getPrice());
            Purchase purchaseForSecondSearch = new Purchase(Constants.ELEMENT_BREAD,
                    new Byn(170), 0);
            findAndShow(firstPurchaseMap, purchaseForSecondSearch,
                    Constants.FIRST_DAY_ELEMENT_INFO + purchaseForSecondSearch.getPrice());
            removeElements(lastPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return Constants.ELEMENT_MEAT.equals(entry.getKey().getProductName());
                }
            });
            removeElements(firstPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return WeekDay.FRIDAY.equals(entry.getValue());
                }
            });
            printMap(firstPurchaseMap, Constants.FIRST_PURCHASE_MAP);
            printMap(lastPurchaseMap, Constants.LAST_PURCHASE_MAP);
            getTotalCost(pricePurchases);
            printMap(dayPurchaseMap, Constants.ENUMERATED_MAP);
            for (Map.Entry<WeekDay, List<Purchase>> entry : dayPurchaseMap.entrySet()) {
                System.out.print(entry.getKey() + Constants.SPACE);
                getTotalCost(entry.getValue());
            }
            findAndShow(dayPurchaseMap, WeekDay.MONDAY, Constants.SEARCH_ELEMENT_MONDAY);
            removeElements(dayPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<WeekDay, List<Purchase>> entry) {
                    boolean haveMilk = false;
                    for (Purchase purchase : entry.getValue()) {
                        if (Constants.PRODUCT_NAME_MILK.equals(purchase.getProductName())) {
                            haveMilk = true;
                            break;
                        }
                    }
                    return haveMilk;
                }
            });
            printMap(dayPurchaseMap, Constants.ENUMERATED_MAP);


        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_IS_NOT_FOUND);
        }
    }

    private static <K, V> void printMap(Map<K, V> currentMap, String massage) {
        System.out.println(massage);
        for (Map.Entry<K, V> entry : currentMap.entrySet()) {
            System.out.println(entry.getKey() + Constants.ARROW + entry.getValue());
        }
        System.out.println();
    }

    private static <K, V> void findAndShow(Map<K, V> currentMap, K searchKey, String header) {
        V requiredElement = currentMap.get(searchKey);
        System.out.println(header + Constants.ARROW + (requiredElement != null ?
                requiredElement.toString() :
                Constants.IS_NOT_FOUND));
    }


    private static <K, V> void removeElements(Map<K, V> currentMap,
                                              EntryChecker<K, V> entryChecker) {
        for (Iterator<Map.Entry<K, V>> it = currentMap.entrySet().iterator(); it.hasNext(); ) {
            if (entryChecker.check(it.next())) {
                it.remove();
            }
        }
    }

    private static void getTotalCost(List<? extends Purchase> purchases) {
        Byn totalCost = new Byn();
        for (Purchase p : purchases) {
            totalCost = totalCost.add(p.getCost());
        }
        System.out.println(Constants.TOTAL_COST + totalCost);
    }
}
