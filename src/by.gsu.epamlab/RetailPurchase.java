package by.gsu.epamlab;

import java.util.Scanner;

public class RetailPurchase extends Purchase {
    private Byn discount;

    public RetailPurchase() {
    }


    public RetailPurchase(String productName, Byn price, int numberOfUnits, Byn discount) {
        super(productName, price, numberOfUnits);
        this.discount = discount;
    }

    public RetailPurchase(Scanner scanner) {
        super(scanner);
        discount = new Byn(scanner);
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).diff(discount).mul(getNumberOfUnits());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}
