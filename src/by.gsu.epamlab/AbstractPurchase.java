package by.gsu.epamlab;

import java.util.Scanner;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private Product product;
    private int numberOfUnits;

    public AbstractPurchase() {

    }

    public AbstractPurchase(Product product, int numberOfUnits) {
        this.product = product;
        this.numberOfUnits = numberOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Byn getCost() {
        return costMethod().round(RoundMethod.FLOOR, 2);
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase abstractPurchase) {
        return abstractPurchase.getCost().getCoins() - this.getCost().getCoins();
    }

    protected String fieldsToString() {
        return product + ";" + numberOfUnits;
    }

    protected abstract Byn costMethod();
}
