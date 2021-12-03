package by.epam.lab;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private String name;
    private Byn price;
    private int numberOfUnits;
    private Byn discount;

    public PriceDiscountPurchase() {

    }


    public PriceDiscountPurchase(String name, Byn price, int numberOfUnits, Byn discount) {
        super(name,price,numberOfUnits);
        discount = new Byn(discount);
    }

    public PriceDiscountPurchase(String[] elements) {
        super(elements);
        discount = new Byn(Integer.parseInt(elements[3]));
    }

//    public PriceDiscountPurchase(Scanner scanner) {
//        super(scanner);
//        try {
//            this.discount = new Byn(scanner);
//        } catch (NumberFormatException | InputMismatchException e){
//            System.out.println("err DiscPurchase");
//        }
//
//    }


    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = new Byn(discount);
    }

    public Byn getCost (){
        return new Byn(super.getPrice()).diff(discount).mul(getNumberOfUnits());
    }


    @Override
    public String toString() {
        return super.toString() + ";" + discount;
    }
}
