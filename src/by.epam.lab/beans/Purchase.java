package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Purchase implements Comparable<Purchase> {
    private AbstractItem item;
    private double quantityOfItem;

    public Purchase() {
    }

    public Purchase(AbstractItem item, double quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public AbstractItem getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public double getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(double quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    public Byn getCost() {
        return item.getPrice().mul(quantityOfItem, RoundMethod.ROUND, 0);
    }

    @Override
    public int compareTo(Purchase o) {
        return this.getCost().compareTo(o.getCost());
    }

    @Override
    public String toString() {
        return item + Constants.SEMICOLON + quantityOfItem;
    }
}
