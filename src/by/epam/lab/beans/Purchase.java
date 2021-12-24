package by.epam.lab.beans;

import by.epam.lab.Constants;
import java.util.Scanner;

public class Purchase {
    private String productName;
    private Byn price;
    private int numberOfUnits;


    public Purchase() {

    }

    public Purchase(String productName, Byn price, int numberOfUnits) {
        this.productName = productName;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Purchase(Scanner scanner) {
        this.productName = scanner.next();
        this.price = new Byn(scanner);
        this.numberOfUnits = scanner.nextInt();
    }

    public Purchase(String[] elements) {
        this(elements[Constants.PURCHASE_NAME_ID],
                new Byn(Integer.parseInt(elements[Constants.PURCHASE_PRICE_ID])),
                Integer.parseInt(elements[Constants.PURCHASE_NUMBER_ID]));
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }


    public Byn getCost() {
        return price.mul(numberOfUnits);
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.SEMICOLON + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return this.productName.equals(purchase.productName) && this.price.equals(purchase.price);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + Constants.SEMICOLON + productName +
                Constants.SEMICOLON + price + Constants.SEMICOLON + numberOfUnits;
    }

    @Override
    public int hashCode() {
        int hash = productName != null ? productName.hashCode() : 0;
        hash = hash * Constants.NUMBER_TO_HASH_MUL_PURCHASES +
                (price != null ? price.hashCode() : 0);
        return hash;
    }
}
