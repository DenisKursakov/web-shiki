package by.gsu.epamlab;

import java.util.Scanner;

public class WholesalePurchase extends Purchase {
    private final int unitNumber;
    private double discountPercent;

    public WholesalePurchase() {
        this(null, null, 0);
    }

    public WholesalePurchase(String productName, Byn price, int numberOfUnits) {
        super(productName, price, numberOfUnits);
        this.unitNumber = 0;
    }

    public WholesalePurchase(Scanner scanner) {
        super(scanner);
        this.unitNumber = scanner.nextInt();
        this.discountPercent = scanner.nextDouble();
    }

    public int getUnitsNumber() {
        return unitNumber;
    }

    @Override
    public Byn getCost() {
        return unitNumber <= super.getNumberOfUnits()
                ? new Byn(super.getPrice().increase(
                super.getNumberOfUnits() * (1 - discountPercent / 100)))
                : new Byn(super.getPrice().increase(super.getNumberOfUnits()));
    }

    @Override
    public String toString() {
        return super.fieldsToString() + ";" + discountPercent + ";" + getCost();
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + unitNumber + ";" + discountPercent;
    }
}
