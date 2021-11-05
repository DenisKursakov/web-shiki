package by.gsu.epamlab;

import java.util.Scanner;

public class PurchasesFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new Purchase(scanner);
            }
        },
        WHOLESALE_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new WholesalePurchase(scanner);
            }
        },
        RETAIL_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new RetailPurchase(scanner);
            }
        };

        abstract Purchase getPurchase(Scanner scanner);
    }

    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        return PurchaseKind.valueOf(scanner.next()).getPurchase(scanner);
    }
}
