package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Purchase<T extends Priceable, T2 extends Number> {
    private T item;
    private T2 quantityOfItem;

    public Purchase() {
    }

    public Purchase(T item, T2 quantityOfItem) {
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

    public void setQuantityOfItem(T2 quantityOfItem) {
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
