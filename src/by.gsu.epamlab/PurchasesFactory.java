package by.gsu.epamlab;

import java.util.Scanner;

public class PurchasesFactory {
    private static enum PurchaseKind {
        GENERAL_PURCHASE,
        WHOLESALE_PURCHASE,
        RETAIL_PURCHASE
    }

    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        String id = scanner.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);
        switch (kind) {
            case GENERAL_PURCHASE:
                return new Purchase(scanner);
            case WHOLESALE_PURCHASE:
                return new WholesalePurchase(scanner);
            case RETAIL_PURCHASE:
                return new RetailPurchase(scanner);
            default:
                throw new IllegalArgumentException();

        }
    }
}
