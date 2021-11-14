package by.gsu.epamlab;

import java.util.Scanner;

public class RetailPurchase extends AbstractPurchase {
    private final Byn discount;

    public RetailPurchase() {
        super();
        this.discount = new Byn(0);
    }

    public RetailPurchase(Product product, int numberOfUnits, Byn discount) {
        super(product, numberOfUnits);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }


    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

    @Override
    protected Byn costCalculation(Byn baseCost) {
        return new Byn(baseCost).diff(new Byn(discount).mul(getNumberOfUnits()));
    }
}
