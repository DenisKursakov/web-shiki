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
        this.productName = scanner.next();
        this.price = new Byn(scanner);
        this.numberOfUnits = scanner.nextInt();
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
        return new Byn(price).mul(getNumberOfUnits());
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
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
        return productName.equals(purchase.productName) && price.equals(purchase.price);
    }

    protected String fieldsToString() {
        return productName + ";" + price + ";" + numberOfUnits;
    }

}
