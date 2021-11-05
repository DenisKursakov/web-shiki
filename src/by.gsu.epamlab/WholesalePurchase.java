package by.gsu.epamlab;

import java.util.Scanner;

public class WholesalePurchase extends AbstractPurchase {
    private final static int UNIT_NUMBER = 15;
    private double discountPercent;

    public WholesalePurchase() {

    }

    public WholesalePurchase(Product product, int numberOfUnits, double discountPercent) {
        super(product, numberOfUnits);
        this.discountPercent = discountPercent;
    }


    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }

    @Override
    protected Byn costCalculation() {
        Byn byn = new Byn(getProduct().getPrice()).mul(getNumberOfUnits());
        if (getNumberOfUnits() > UNIT_NUMBER) {
            byn.mul(1 - discountPercent / 100, RoundMethod.FLOOR, 2);
        }
        return byn;
    }
}
