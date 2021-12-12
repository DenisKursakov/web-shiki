package by.epam.lab.beans;

public abstract class AbstractPurchase<T extends Priceable> {
    abstract Byn getCost();
}
