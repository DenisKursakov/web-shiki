package by.epam.lab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private int valueInKopecks;

    public Byn() {
    }

    public Byn(int valueInKopecks) {
        if(valueInKopecks <= 0){
            throw new IllegalArgumentException();
        }
        this.valueInKopecks = valueInKopecks;
    }

    public Byn(Scanner scanner) {
        this(scanner.nextInt());
    }

    public Byn(int rubs, int coins) {
        this(rubs * Constants.HUNDRED + coins);
    }

    public Byn(Byn byn) {
        this(byn.valueInKopecks);
    }

    public Byn add(Byn byn) {
        valueInKopecks += byn.valueInKopecks;
        return this;
    }

    public int getRubs() {
        return valueInKopecks / Constants.HUNDRED;
    }

    public int getCoins() {
        return valueInKopecks;
    }

    public Byn diff(Byn byn) {
        valueInKopecks -= byn.valueInKopecks;
        return this;
    }

    public Byn mul(int a) {
        valueInKopecks *= a;
        return this;
    }


    @Override
    public String toString() {
        return String.format(Constants.CONVERT_FORMAT,
                valueInKopecks / Constants.HUNDRED, valueInKopecks % Constants.HUNDRED);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Byn byn = (Byn) obj;
        return valueInKopecks == byn.valueInKopecks;
    }

    @Override
    public int compareTo(Byn byn) {
        return valueInKopecks - byn.valueInKopecks;
    }

}
