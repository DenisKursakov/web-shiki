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


    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public WholesalePurchase(Scanner scanner) {
        super(scanner);
        this.discountPercent = scanner.nextDouble();
    }


    @Override
    public Byn getCost() {
        Byn byn = new Byn();
        if (UNIT_NUMBER <= super.getNumberOfUnits()) {
            byn = new Byn().sum(getPrice()).increase(
                    getNumberOfUnits() * (1 - discountPercent / 100));
        } else {
            byn = super.getCost();
        }
        return byn;

    }


    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }
}
