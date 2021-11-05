import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final Product PRODUCT = new Product("milk", new Byn(140));
        AbstractPurchase[] purchases = {
                new RetailPurchase(PRODUCT, 8, new Byn(20)),
                new DeliveredPurchase(PRODUCT, 2, new Byn(125)),
                new WholesalePurchase(PRODUCT, 17, 75.225),
                new RetailPurchase(PRODUCT, 11, new Byn(24)),
                new WholesalePurchase(PRODUCT, 12, 25.75),
                new DeliveredPurchase(PRODUCT, 9, new Byn(155))
        };
        showPurchases(purchases);
        Arrays.sort(purchases);
        showPurchases(purchases);
        System.out.println("Min cost = " + purchases[purchases.length - 1].getCost());
        int requiredElement = Arrays.binarySearch(purchases, new RetailPurchase(
                PRODUCT, 4, new Byn(10)));
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
