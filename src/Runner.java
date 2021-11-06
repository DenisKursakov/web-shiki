import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final Product MILK = new Product("milk", new Byn(500));
        AbstractPurchase[] purchases = {
                new RetailPurchase(MILK, 2, new Byn(250)),
                new DeliveredPurchase(MILK, 2, new Byn(125)),
                new WholesalePurchase(MILK, 17, 5.225),
                new RetailPurchase(MILK, 11, new Byn(24)),
                new WholesalePurchase(MILK, 12, 25.75),
                new DeliveredPurchase(MILK, 9, new Byn(155))
        };
        showPurchases(purchases);
        Arrays.sort(purchases);
        showPurchases(purchases);
        System.out.println("Min cost = " + purchases[purchases.length - 1].getCost());
        int requiredElement = Arrays.binarySearch(purchases, new RetailPurchase(
                MILK, 1, new Byn(0)));
        if (requiredElement >= 0) {
            System.out.println("Required element: " + purchases[requiredElement]);
        } else {
            System.out.println("Required element is not found");
        }

    }

    private static void showPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }
}