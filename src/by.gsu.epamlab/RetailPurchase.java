package by.gsu.epamlab;

import java.util.Scanner;

public class RetailPurchase extends AbstractPurchase {
    private Byn discount;

    public RetailPurchase() {

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
    protected Byn costMethod() {
        return new Byn(getProduct().diffPrice(discount)).mul(getNumberOfUnits());
    }
}
