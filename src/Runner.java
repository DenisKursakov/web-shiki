
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
        try (Scanner scanner = new Scanner(new FileReader("src/in.csv"))) {
            scanner.useLocale(Locale.ENGLISH);
            Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
            Map<Purchase, WeekDay> lastPurchaseMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> enumerationMap = new HashMap<>();
            List<PricePurchase> pricePurchases = new ArrayList<>();
            Byn totalCostPurchases = new Byn();
            while (scanner.hasNextLine()) {
                Purchase currentPurchase = PurchasesFactory.getPurchaseFromFactory(scanner);
                if (currentPurchase.getClass() == PricePurchase.class) {
                    pricePurchases.add((PricePurchase) currentPurchase);
                    totalCostPurchases.add(currentPurchase.getCost());
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
            printMap(firstPurchaseMap);
            printMap(lastPurchaseMap);
            System.out.println("First day bread 1.55 = " +
                    searchElement(firstPurchaseMap,
                            new Purchase("bread", new Byn(155), 0)));
            System.out.println("Last day bread 1.55 = " +
                    searchElement(lastPurchaseMap,
                            new Purchase("bread", new Byn(155), 0)));
            System.out.println("First day bread 1.70 = " +
                    searchElement(firstPurchaseMap,
                            new Purchase("bread", new Byn(170), 0)));
            removeElement(firstPurchaseMap, new Purchase("meat",
                    new Byn(0), 0).getProductName());
            removeElement(lastPurchaseMap, WeekDay.FRIDAY.name());
            printMap(firstPurchaseMap);
            printMap(lastPurchaseMap);
            printTotalCost(pricePurchases);
            printMap(enumerationMap);
            for (Map.Entry<WeekDay, List<Purchase>> entry : enumerationMap.entrySet()) {
                System.out.print(entry.getKey() + " ");
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
        for (Map.Entry<?, ?> entry : currentMap.entrySet()) {
            if (entry.toString().contains(key)) {
                resultInfo = entry.getKey() + " was deleted";
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
        System.out.println("Total cost = " + totalCost);
    }
}
