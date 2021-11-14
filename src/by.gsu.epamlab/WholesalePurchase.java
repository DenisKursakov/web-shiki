package by.gsu.epamlab;

import java.util.Scanner;

public class WholesalePurchase extends AbstractPurchase {
    private final static int UNIT_NUMBER = 15;
    private final double discountPercent;

    public WholesalePurchase() {
        super();
        this.discountPercent = 0.0;
    }

    public WholesalePurchase(Product product, int numberOfUnits, double discountPercent) {
        super(product, numberOfUnits);
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discountPercent;
    }

    @Override
    protected Byn costCalculation(Byn baseCost) {
        Byn byn = new Byn(baseCost);
        if (getNumberOfUnits() > UNIT_NUMBER) {
            byn = new Byn(baseCost).mul(1 - discountPercent / 100, RoundMethod.FLOOR, 0);
        }
        return byn;
    }
}
