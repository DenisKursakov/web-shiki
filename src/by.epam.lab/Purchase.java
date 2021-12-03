package by.epam.lab;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Purchase implements Comparable<Purchase>{
    private String name;
    private Byn price;
    private int numberOfUnits;

    public Purchase() {

    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        this.name = name;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }
    public Purchase(String[] elements){
        this.name = elements[0];
        this.price = new Byn(Integer.parseInt(elements[1]));
        this.numberOfUnits = Integer.parseInt(elements[2]);
    }

    public Purchase (Scanner scanner){
        try {
            this.name = scanner.next();
            this.price = new Byn(scanner);
            this.numberOfUnits = scanner.nextInt();
        }catch (NumberFormatException | InputMismatchException e){
            System.out.println("err Purchase");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }
    public Byn getCost (){
        return new Byn(price).mul(numberOfUnits);
    }

    @Override
    public String toString() {
        return name + ";" + price + ";" + numberOfUnits + ";" + getCost();
    }

    @Override
    public int compareTo(Purchase o) {
        if(this.name.equals(o.getName())){
            if(o instanceof PriceDiscountPurchase && !(this instanceof PriceDiscountPurchase)){
                return -1;
            }
            if(this instanceof PriceDiscountPurchase && !(o instanceof PriceDiscountPurchase)){
                return 1;
            }
            return this.getCost().compareTo(o.getCost());
        }
        return this.name.compareTo(o.getName());
    }
    public int compareTo2(Purchase o){
        if(this.name.equals(o.getName())){
            if(o.getClass() == this.getClass()){
                return this.getCost().compareTo(o.getCost());
            }
            if(o.getClass() == PriceDiscountPurchase.class){
                return -1;
            } else {
                return 1;
            }
        }
        return this.name.compareTo(o.getName());
    }

}
