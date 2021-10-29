package by.gsu.epamlab;


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
        this(scanner.next(), new Byn(scanner.nextInt()), scanner.nextInt());
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
        return new Byn().sum(price).increase(getNumberOfUnits());
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Purchase purchase = (Purchase) obj;
        return price.equals(purchase.price) && productName.equals(purchase.productName);
    }

    protected String fieldsToString() {
        return productName + ";" + price + ";" + numberOfUnits;
    }

}
