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
            final int PURCHASES_NUMBER = Integer.parseInt(scanner.nextLine());
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            for (int i = 0; i < purchases.length; i++) {
                String[] st = scanner.nextLine().split(" ");
                purchases[i] = new Purchase(
                        Integer.parseInt(st[0]),
                        Integer.parseInt(st[1]),
                        WeekDay.values()[Integer.parseInt(st[2])]);
            }

            for (int i = 0; i < purchases.length; i++) {
                if (i == 0) {
                    System.out.println("Purchases number: " + PURCHASES_NUMBER);
                    System.out.println(purchases[i].showConstant());
                }
                System.out.println(purchases[i]);
            }
            double sumOfCost = 0.0;
            double sumOfMonday = 0.0;
            Purchase DayWithMaxCostPurchase = new Purchase();

            for (Purchase purchase : purchases) {
                if (DayWithMaxCostPurchase.getCost() < purchase.getCost()) {
                    DayWithMaxCostPurchase = purchase;
                }
                if (purchase.getWeekDay().equals(WeekDay.MONDAY)) {
                    sumOfMonday += purchase.getCost();
                }
                sumOfCost += purchase.getCost();
            }

            double meanCost = PURCHASES_NUMBER != 0 ? sumOfCost / PURCHASES_NUMBER : 0.0;
            System.out.printf("Mean cost = %.3f\n", meanCost);
            System.out.printf("Total cost of Monday = %.2f\n", sumOfMonday);
            System.out.println("Day with the max cost is " + DayWithMaxCostPurchase.getWeekDay());

            Arrays.sort(purchases);
            for (int i = 0; i < purchases.length; i++) {
                if (i == 0) {
                    System.out.println("Purchases number: " + PURCHASES_NUMBER);
                    System.out.println(purchases[i].showConstant());
                }
                System.out.println(purchases[i]);
            }
            Purchase purchase = new Purchase(5, 35, WeekDay.FRIDAY);
            if (PURCHASES_NUMBER != 0 && Arrays.binarySearch(purchases, purchase) >= 0) {
                System.out.println("Required element: " +
                        purchases[Arrays.binarySearch(purchases, purchase)]);
            } else System.out.println("Required element is not found");
        } catch (FileNotFoundException e) {
            System.err.println("File is not found");
        }

    }

}
