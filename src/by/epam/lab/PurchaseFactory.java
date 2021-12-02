package by.epam.lab;

import java.util.Scanner;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            @Override
            Purchase getPurchase(Scanner scanner) {
                return new Purchase(scanner);
            }
        },
        DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(Scanner scanner) {
                return new PriceDiscountPurchase(scanner);
            }
        };
        abstract Purchase getPurchase(Scanner scanner);
    }
    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        int count = scanner.nextLine().split(";").length;
        return count == 4 ? PurchaseKind.DISCOUNT_PURCHASE.getPurchase(scanner) :
                PurchaseKind.GENERAL_PURCHASE.getPurchase(scanner);
    }
}
