package by.epam.lab;

import java.util.Scanner;

public class Purchase {
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

    public Purchase (Scanner scanner){
        this.name = scanner.next();
        System.out.println(name);
        this.price = new Byn(Integer.parseInt(scanner.next()));
        System.out.println(price);
        this.numberOfUnits = Integer.parseInt(scanner.next());
        System.out.println(numberOfUnits);

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

    @Override
    public String toString() {
        return name + ";" + price + ";" + numberOfUnits;
    }
}
