package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Scanner;

public class PricePurchase extends Purchase {
    private Byn discount;

    public PricePurchase() {
    }


    public PricePurchase(String productName, Byn price, int numberOfUnits, Byn discount) {
        super(productName, price, numberOfUnits);
        this.discount = discount;
    }

    public PricePurchase(Scanner scanner) {
        super(scanner);
        discount = new Byn(scanner);
    }

    public PricePurchase(String[] elements) {
        super(elements);
        discount = new Byn(Integer.parseInt(elements[Constants.PURCHASE_DISCOUNT_ID]));
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
        return super.fieldsToString() + Constants.SEMICOLON + discount;
    }
}
