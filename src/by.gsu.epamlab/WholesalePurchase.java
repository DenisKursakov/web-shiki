package by.gsu.epamlab;

import java.util.Scanner;

public class WholesalePurchase extends Purchase {
    private final static int UNIT_NUMBER = 15;
    private double discountPercent;

    public WholesalePurchase() {

    }

    public WholesalePurchase(String productName, Byn price,
                             int numberOfUnits, double discountPercent) {
        super(productName, price, numberOfUnits);
        this.discountPercent = discountPercent;
    }

    public WholesalePurchase(Scanner scanner) {
        super(scanner);
        this.discountPercent = scanner.nextDouble();
    }


    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }


    @Override
    public Byn getCost() {
        Byn byn = super.getCost();
        if (getNumberOfUnits() > UNIT_NUMBER) {
            byn.mul(1 - discountPercent / 100, RoundMethod.ROUND, 0);
        }
        return byn;
    }


    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }
}
