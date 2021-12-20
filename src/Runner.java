
import by.epam.lab.Constants;
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
            Map<WeekDay, List<Purchase>> enumerationMap = new HashMap<>();
            List<PricePurchase> pricePurchases = new ArrayList<>();
            while (scanner.hasNextLine()) {
                Purchase currentPurchase = PurchasesFactory.getPurchaseFromFactory(scanner);
                if (currentPurchase.getClass() == PricePurchase.class) {
                    pricePurchases.add((PricePurchase) currentPurchase);
                }

                WeekDay currentPurchaseDay = WeekDay.valueOf(scanner.nextLine());
                if (!firstPurchaseMap.containsKey(currentPurchase)) {
                    firstPurchaseMap.put(currentPurchase, currentPurchaseDay);
                }
                lastPurchaseMap.put(currentPurchase, currentPurchaseDay);
                List<Purchase> currentPurchases = new ArrayList<>();
                currentPurchases.add(currentPurchase);
                if (enumerationMap.containsKey(currentPurchaseDay)) {
                    enumerationMap.get(currentPurchaseDay).add(currentPurchase);
                } else {
                    enumerationMap.put(currentPurchaseDay, currentPurchases);
                }

            }
            printMap(lastPurchaseMap);
            printMap(firstPurchaseMap);
            System.out.println(Constants.FIRST_DAY_ELEMENT_INFO +
                    searchElement(firstPurchaseMap,
                            new Purchase(Constants.ELEMENT_BREAD,
                                    new Byn(155), 0)));
            System.out.println(Constants.LAST_DAY_ELEMENT_INFO +
                    searchElement(lastPurchaseMap,
                            new Purchase(Constants.ELEMENT_BREAD,
                                    new Byn(155), 0)));
            System.out.println(Constants.FIRST_DAY_ELEMENT_INFO +
                    searchElement(firstPurchaseMap,
                            new Purchase(Constants.ELEMENT_BREAD,
                                    new Byn(170), 0)));
            removeElement(lastPurchaseMap, new Purchase(Constants.ELEMENT_MEAT,
                    new Byn(0), 0).getProductName());
            removeElement(firstPurchaseMap, WeekDay.FRIDAY.name());
            printMap(lastPurchaseMap);
            printMap(firstPurchaseMap);
            printTotalCost(pricePurchases);
            printMap(enumerationMap);
            for (Map.Entry<WeekDay, List<Purchase>> entry : enumerationMap.entrySet()) {
                System.out.print(entry.getKey() + Constants.SPACE);
                printTotalCost(entry.getValue());
            }
            System.out.println(searchElement(enumerationMap, WeekDay.MONDAY));


        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_IS_NOT_FOUND);
        }
    }

    private static <E extends Map<?, ?>> void printMap(E currentMap) {
        for (Map.Entry<?, ?> entry : currentMap.entrySet()) {
            System.out.println(entry.getKey() + Constants.ARROW + entry.getValue());
        }
        System.out.println();
    }

    private static <K, E extends Map<?, ?>> String searchElement(E currentMap, K key) {
        String requiredDay = Constants.REQUIRED_IS_NOT_FOUND;
        if (currentMap.containsKey(key)) {
            requiredDay = currentMap.get(key).toString();
        }
        return requiredDay;
    }

    private static <E extends Map<?, ?>> void removeElement(E currentMap, String key) {
        String resultInfo = Constants.REQUIRED_IS_NOT_FOUND;
        Iterator<? extends Map.Entry<?, ?>> iterator = currentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<?, ?> entry = iterator.next();
            if (entry.toString().contains(key)) {
                resultInfo = entry.getKey() + Constants.WAS_DELETED;
                iterator.remove();
                System.out.println(resultInfo);
            }
        }
        if (resultInfo.equals(Constants.REQUIRED_IS_NOT_FOUND)) {
            System.out.println(resultInfo);
        }
    }

    private static <E extends List<? extends Purchase>> void printTotalCost(E list) {
        Byn totalCost = new Byn();
        for (Purchase p : list) {
            totalCost = totalCost.add(p.getCost());
        }
        System.out.println(Constants.TOTAL_COST + totalCost);
    }
}
