package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.exceptions.NonPositiveArgumentException;

public class Purchase {
    private String name;
    private Byn price;
    private int numberOfUnits;

    public Purchase() {

    }

    public Purchase(Purchase purchase) {
        this(purchase.name, purchase.price, purchase.numberOfUnits);
    }

    public Purchase(String name, int price, int numberOfUnits) {
        setName(name);
        setPrice(new Byn(price));
        setNumberOfUnits(numberOfUnits);

    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        setName(name);
        setPrice(price);
        setNumberOfUnits(numberOfUnits);
    }


    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (name.isEmpty()) {
            throw new NullPointerException();
        }
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public final void setPrice(Byn price) {
        if (price == null) {
            throw new NullPointerException();
        }
        if (price.compareTo(new Byn(0)) <= 0) {
            throw new NonPositiveArgumentException(Constants.PRICE, price.toString());
        }
        this.price = price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public final void setNumberOfUnits(int numberOfUnits) {
        if (numberOfUnits <= 0) {
            throw new NonPositiveArgumentException(Constants.NUMBER, String.valueOf(numberOfUnits));
        }
        this.numberOfUnits = numberOfUnits;
    }

    public Byn getCost() {
        return new Byn(price).mul(numberOfUnits);
    }

    public String lineToTableFormat() {
        return String.format(Constants.FORMAT_TO_TABLE, getName(), getPrice(),
                getNumberOfUnits(), Constants.MINUS, getCost());
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.SEMICOLON + getCost();
    }

    protected String fieldsToString() {
        return name + Constants.SEMICOLON + price + Constants.SEMICOLON + numberOfUnits;
    }

}
