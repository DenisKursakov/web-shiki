package by.epam.lab;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private String name;
    private Byn price;
    private int numberOfUnits;
    private double discountPercent;

    public PriceDiscountPurchase() {

    }

    public PriceDiscountPurchase(String name, Byn price, int numberOfUnits, double discountPercent) {
        super(name,price,numberOfUnits);
        this.discountPercent = discountPercent;
    }

    public PriceDiscountPurchase(Scanner scanner) {
        super(scanner);
        this.discountPercent = scanner.nextDouble();
    }


    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + discountPercent;
    }
}
