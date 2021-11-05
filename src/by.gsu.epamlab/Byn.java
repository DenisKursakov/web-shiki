package by.gsu.epamlab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private int valueInKopecks;

    public Byn() {
    }

    public Byn(int valueInKopecks) {
        this.valueInKopecks = valueInKopecks;
    }

    public Byn(Scanner scanner) {
        this(scanner.nextInt());
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.valueInKopecks);
    }

    public Byn add(Byn byn) {
        valueInKopecks += byn.valueInKopecks;
        return this;
    }

    public int getRubs() {
        return valueInKopecks / 100;
    }

    public int getCoins() {
        return valueInKopecks;
    }

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        valueInKopecks = roundMethod.round(valueInKopecks * k, d);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        valueInKopecks = roundMethod.round(valueInKopecks, d);
        return this;
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
        return String.format("%d.%02d", valueInKopecks / 100, valueInKopecks % 100);
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