package by.epam.lab.factories;

import by.epam.lab.beans.PriceDiscountPurchase;
import by.epam.lab.Constants;
import by.epam.lab.beans.Purchase;

import java.util.Scanner;

public class PurchasesFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(String[] elements) {
                return new Purchase(elements);
            }
        },
        PRICE_PURCHASE {
            Purchase getPurchase(String[] elements) {
                return new PriceDiscountPurchase(elements);
            }
        };

        abstract Purchase getPurchase(String[] elements);
    }

    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        String[] elements = scanner.nextLine().split(Constants.SEMICOLON);
        PurchaseKind kind = PurchaseKind.values()[elements.length -
                Constants.MAX_IN_LINE_LENGTH + 1];
        return kind.getPurchase(elements);

    }
}
