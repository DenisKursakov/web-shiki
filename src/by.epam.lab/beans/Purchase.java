package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Purchase<T extends Priceable> extends AbstractPurchase<Priceable> {
    private T item;
    private Number quantityOfItem;

    public Purchase() {
    }

    public Purchase(T item, Number quantityOfItem) {
        this.item = item;
        this.quantityOfItem = quantityOfItem;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
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
    public String toString() {
        return item + Constants.SEMICOLON + quantityOfItem + Constants.SEMICOLON + getCost();
    }
}
