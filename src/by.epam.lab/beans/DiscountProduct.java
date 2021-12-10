package by.epam.lab.beans;

import by.epam.lab.Constants;

public class DiscountProduct extends Product {
    private Byn discount;

    public DiscountProduct() {
        super();
    }

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);
        this.discount = discount;
    }

    @Override
    public Byn getPrice() {
        return super.getPrice().diff(discount);
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    protected String fieldToString() {
        return super.fieldToString() + Constants.SEMICOLON + discount;
    }
}
