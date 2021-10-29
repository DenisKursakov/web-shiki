package by.gsu.epamlab;

import java.util.Scanner;

public class RetailPurchase extends Purchase {
    private int discount;

    public RetailPurchase() {
    }

    public RetailPurchase(String productName, Byn price, int numberOfUnits) {
        super(productName, price, numberOfUnits);
    }

    public RetailPurchase(Scanner scanner) {
        super(scanner);
        this.discount = scanner.nextInt();
    }

    @Override
    public Byn getCost() {
        return new Byn(super.getPrice().diff(discount) * super.getNumberOfUnits());
    }

    @Override
    public String toString() {
        return super.fieldsToString() + ";" + discount + ";" + getCost();
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

}
