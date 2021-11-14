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

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }

    @Override
    protected Byn costCalculation(Byn baseCost) {
        Byn byn = baseCost;
        if (getNumberOfUnits() > UNIT_NUMBER) {
            byn = baseCost.mul(1 - discountPercent / 100, RoundMethod.FLOOR, 0);
        }
        return byn;
    }
}
