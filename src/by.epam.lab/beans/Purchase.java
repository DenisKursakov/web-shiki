package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Purchase implements Comparable<Purchase> {
    private Priceable item;
    private Number quantityOfItem;

    public Purchase() {
    }

    public Purchase(Priceable item, Number quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public Priceable getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public Number getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(double quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    public Byn getCost() {
        return item.getPrice().mul(quantityOfItem.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public int compareTo(Purchase o) {
        return this.getCost().compareTo(o.getCost());
    }

    @Override
    public String toString() {
        return item + Constants.SEMICOLON + quantityOfItem + Constants.SEMICOLON + getCost();
    }
}
