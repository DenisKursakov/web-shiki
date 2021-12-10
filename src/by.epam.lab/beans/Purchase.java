package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Purchase implements Comparable<Purchase> {
    private AbstractItem item;
    private Number quantityOfItem;

    public Purchase() {
    }

    public Purchase(AbstractItem item, Number quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public AbstractItem getItem() {
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
        return quantityOfItem.getClass() == Integer.class ?
                item.getPrice().mul((int) quantityOfItem, RoundMethod.ROUND, 0) :
                item.getPrice().mul((double) quantityOfItem, RoundMethod.ROUND, 0);
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
