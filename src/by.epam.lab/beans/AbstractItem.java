package by.epam.lab.beans;

import by.epam.lab.Constants;

public abstract class AbstractItem {
    private String name;
    private Byn price;

    public AbstractItem(){

    }

    public AbstractItem(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public Byn getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return fieldToString();
    }

    protected String fieldToString() {
        return name + Constants.SEMICOLON + price;
    }
}
