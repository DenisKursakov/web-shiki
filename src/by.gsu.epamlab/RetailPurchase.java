package by.gsu.epamlab;

import java.util.Scanner;

public class RetailPurchase extends AbstractPurchase {
    private Byn discount;

    public RetailPurchase() {
        super();
    }

    public RetailPurchase(Product product, int numberOfUnits, Byn discount) {
        super(product, numberOfUnits);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

    @Override
    protected Byn costCalculation(Byn baseCost) {
        return baseCost.diff(discount.mul(getNumberOfUnits()));
    }
}
