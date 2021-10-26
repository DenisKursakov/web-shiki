package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {
    private final static String PRODUCT_NAME = "Coffee";
    private final static int PRICE = 782;                           //bunks
    private int numberOfUnits;
    private double discountPercent;
    private WeekDay weekDay;


    public Purchase() {

    }

    public Purchase(int numberOfUnits, double discountPercent, WeekDay weekDay) {
        this.numberOfUnits = numberOfUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    }


    public String showConstant() {
        return String.format("Name: %s\nPrice: %s", PRODUCT_NAME, Converter.convert(PRICE));
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }



    public int getCost() {
        return (int) Math.round(
                (PRICE * numberOfUnits * (100.0 - discountPercent) / 100) * 0.01) * 100;
    }

    @Override
    public String toString() {
        return numberOfUnits + ";" + discountPercent + ";"
                + weekDay + ";" + Converter.convert(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return Integer.compare(numberOfUnits, purchase.numberOfUnits);
    }
