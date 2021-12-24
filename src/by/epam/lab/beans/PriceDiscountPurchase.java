package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public PriceDiscountPurchase() {
    }


    public PriceDiscountPurchase(String productName, Byn price, int numberOfUnits, Byn discount) {
        super(productName, price, numberOfUnits);
        this.discount = discount;
    }

    public PriceDiscountPurchase(Scanner scanner) {
        super(scanner);
        discount = new Byn(scanner);
    }

    public PriceDiscountPurchase(String[] elements) {
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
        return getPrice().diff(discount).mul(getNumberOfUnits());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + Constants.SEMICOLON + discount;
    }
}
