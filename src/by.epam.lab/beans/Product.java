package by.epam.lab.beans;

import by.epam.lab.Constants;

public class Product implements Priceable {
    private String name;
    private Byn price;

    public Product() {
    }

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return fieldToString() + Constants.SEMICOLON + getPrice();
    }

    protected String fieldToString() {
        return name + Constants.SEMICOLON + price;
    }
}
