
import by.epam.lab.Constants;
import by.epam.lab.interfaces.EntryChecker;
import by.epam.lab.beans.Byn;
import by.epam.lab.beans.PricePurchase;
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
            List<PricePurchase> pricePurchases = new ArrayList<>();
            while (scanner.hasNextLine()) {
                Purchase currentPurchase = PurchasesFactory.getPurchaseFromFactory(scanner);
                WeekDay currentPurchaseDay = WeekDay.valueOf(scanner.nextLine());
                if (currentPurchase.getClass() == PricePurchase.class) {
                    pricePurchases.add((PricePurchase) currentPurchase);
                }
                lastPurchaseMap.put(currentPurchase, currentPurchaseDay);
                if (!firstPurchaseMap.containsKey(currentPurchase)) {
                    firstPurchaseMap.put(currentPurchase, currentPurchaseDay);
                }
                List<Purchase> keyListPurchases = new ArrayList<>();
                keyListPurchases.add(currentPurchase);
                if (dayPurchaseMap.containsKey(currentPurchaseDay)) {
                    dayPurchaseMap.get(currentPurchaseDay).add(currentPurchase);
                } else {
                    dayPurchaseMap.put(currentPurchaseDay, keyListPurchases);
                }

            }
            printMap(lastPurchaseMap, Constants.LAST_PURCHASE_MAP);
            printMap(firstPurchaseMap, Constants.FIRST_PURCHASE_MAP);
            printMap(dayPurchaseMap, Constants.ENUMERATED_MAP);
            Purchase purchaseForSearch = new Purchase(Constants.ELEMENT_BREAD,
                    new Byn(155), 0);
            searchElement(firstPurchaseMap, purchaseForSearch,
                    Constants.FIRST_DAY_ELEMENT_INFO + purchaseForSearch.getPrice());
            searchElement(lastPurchaseMap, purchaseForSearch,
                    Constants.LAST_DAY_ELEMENT_INFO + purchaseForSearch.getPrice());
            Purchase purchaseForSecondSearch = new Purchase(Constants.ELEMENT_BREAD,
                    new Byn(170), 0);
            searchElement(firstPurchaseMap, purchaseForSecondSearch,
                    Constants.FIRST_DAY_ELEMENT_INFO + purchaseForSecondSearch.getPrice());
            EntryChecker<Purchase, WeekDay> entryCheckerPurchaseNameMeat = new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return entry.getKey().getProductName().equals(Constants.ELEMENT_MEAT);
                }
            };
            removeElement(lastPurchaseMap, entryCheckerPurchaseNameMeat);
            EntryChecker<Purchase, WeekDay> entryCheckerPurchaseDayFriday = new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return entry.getValue().equals(WeekDay.FRIDAY);
                }
            };
            removeElement(firstPurchaseMap, entryCheckerPurchaseDayFriday);
            EntryChecker<WeekDay, List<Purchase>> entryCheckerDayWithMilkPurchase
                    = new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<WeekDay, List<Purchase>> entry) {
                    boolean haveMilk = false;
                    for (String s : entry.getValue().toString().split(Constants.COMMA)) {
                        if (s.split(Constants.SEMICOLON)[1].equals(Constants.PRODUCT_NAME_MILK)) {
                            haveMilk = true;
                            break;
                        }
                    }
                    return haveMilk;
                }
            };
            removeElement(dayPurchaseMap, entryCheckerDayWithMilkPurchase);
            printMap(lastPurchaseMap, Constants.LAST_PURCHASE_MAP);
            printMap(firstPurchaseMap, Constants.FIRST_PURCHASE_MAP);
            System.out.println(Constants.TOTAL_COST + getTotalCost(pricePurchases));
            printMap(dayPurchaseMap, Constants.ENUMERATED_MAP);
            for (Map.Entry<WeekDay, List<Purchase>> entry : dayPurchaseMap.entrySet()) {
                System.out.println(entry.getKey() + Constants.SPACE + Constants.TOTAL_COST +
                        getTotalCost(entry.getValue()));

            }
            searchElement(dayPurchaseMap, WeekDay.MONDAY, Constants.SEARCH_ELEMENT_MONDAY);


        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_IS_NOT_FOUND);
        }
    }

    private static <K, V> void printMap(Map<K, V> currentMap, String massage) {
        System.out.println(massage);
        for (Map.Entry<?, ?> entry : currentMap.entrySet()) {
            System.out.println(entry.getKey() + Constants.ARROW + entry.getValue());
        }
        System.out.println();
    }

    private static <K, V> void searchElement(Map<K, V> currentMap, K searchKey, String header) {
        String requiredElement = Constants.IS_NOT_FOUND;
        if (currentMap.containsKey(searchKey)) {
            requiredElement = currentMap.get(searchKey).toString();
        }
        System.out.println(header + Constants.ARROW + requiredElement);
    }


    private static <K, V> void removeElement(Map<K, V> currentMap,
                                             EntryChecker<K, V> entryChecker) {
        String resultInfo = Constants.REQUIRED_IS_NOT_FOUND;
        Iterator<Map.Entry<K, V>> iterator = currentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            if (entryChecker.check(entry)) {
                resultInfo = entry + Constants.WAS_DELETED;
                iterator.remove();
                System.out.println(resultInfo);
            }
        }
        if (resultInfo.equals(Constants.REQUIRED_IS_NOT_FOUND)) {
            System.out.println(resultInfo);
        }
    }

    private static Byn getTotalCost(List<? extends Purchase> purchases) {
        Byn totalCost = new Byn();
        for (Purchase p : purchases) {
            totalCost = totalCost.add(p.getCost());
        }
        return totalCost;
    }
}
