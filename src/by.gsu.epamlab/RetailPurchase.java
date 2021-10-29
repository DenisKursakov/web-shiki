package by.gsu.epamlab;

import java.util.Scanner;

public class RetailPurchase extends Purchase {
    private static Byn discount;

    public RetailPurchase() {
    }

    public RetailPurchase(String productName, Byn price, int numberOfUnits, Byn discount) {
        super(productName, price, numberOfUnits);
    }

    public RetailPurchase(Scanner scanner) {
        super(scanner);
        discount = new Byn(scanner.nextInt());
    }

    @Override
    public Byn getCost() {
        return new Byn().sum(this.getPrice()).diff(discount).increase(getNumberOfUnits());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

}
