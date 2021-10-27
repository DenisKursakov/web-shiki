import by.gsu.epamlab.Converter;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.WeekDay;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader("src/in.txt"))) {
            scanner.useLocale(Locale.ENGLISH);
            final int PURCHASES_NUMBER = scanner.nextInt();
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = new Purchase(
                        scanner.nextInt(),
                        scanner.nextDouble(),
                        scanner.nextInt());
            }
            showInfo(purchases);
            int sumOfCost = 0;
            int sumOfMonday = 0;
            int maxCost = 0;
            double meanCost = 0.0;
            WeekDay maxCostDay = null;

            for (Purchase purchase : purchases) {
                int cost = purchase.getCost();
                if (maxCost < cost) {
                    maxCost = cost;
                    maxCostDay = purchase.getWeekDay();
                }
                if (purchase.getWeekDay() == WeekDay.MONDAY) {
                    sumOfMonday += cost;
                }
                sumOfCost += cost;
            }

            if (purchases.length > 0) {
                meanCost = (double) sumOfCost / purchases.length / 100;
            }
            System.out.printf("Mean cost = %.3f\n", meanCost);
            System.out.println("Total cost of Monday = " + Converter.convert(sumOfMonday));
            System.out.println("Day with the max cost is " + maxCostDay);

            Arrays.sort(purchases);
            showInfo(purchases);
            Purchase purchase = new Purchase(5, 35, WeekDay.FRIDAY);
            int requiredElement = Arrays.binarySearch(purchases, purchase);
            if (requiredElement >= 0) {
                System.out.println("Required element: " + purchases[requiredElement]);
            } else {
                System.out.println("Required element is not found");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File is not found");
        }

    }

    private static void showInfo(Purchase[] purchases) {
        System.out.printf("Name: %s\nPrice: %s\n",
                Purchase.PRODUCT_NAME, Converter.convert(Purchase.PRICE));
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

}

