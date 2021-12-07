package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.exceptions.CsvLineException;
import by.epam.lab.exceptions.NonPositiveArgumentException;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public PriceDiscountPurchase() {

    }

    public PriceDiscountPurchase(PriceDiscountPurchase purchase) {
        super(purchase);
        setDiscount(purchase.discount);
    }

    public PriceDiscountPurchase(String name, int price, int numberOfUnits, int discount) {
        super(name, price, numberOfUnits);
        setDiscount(new Byn(discount));
    }

    public PriceDiscountPurchase(String name, Byn price, int numberOfUnits, Byn discount) {
        super(name, price, numberOfUnits);
        setDiscount(discount);
    }

    public Byn getDiscount() {
        return discount;
    }

    public final void setDiscount(Byn discount) {
        if (discount == null) {
            throw new NullPointerException();
        }
        if(discount.compareTo(new Byn(0)) <= 0){
            throw new NonPositiveArgumentException(Constants.DISCOUNT, discount.toString());
        }
        this.discount = new Byn(discount);
    }

    public Byn getCost() {
        return new Byn(super.getPrice()).diff(discount).mul(getNumberOfUnits());
    }

    public String lineToTableFormat() {
        return String.format(Constants.FORMAT_TO_TABLE, getName(), getPrice(),
                getNumberOfUnits(), getDiscount(), getCost());
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.SEMICOLON + discount;
    }
}
