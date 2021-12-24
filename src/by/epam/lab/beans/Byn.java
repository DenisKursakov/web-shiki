package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    public Byn(int rubs, int coins) {
        this(rubs * Constants.HUNDRED_FOR_BYN_RESULT + coins);
    }

    public Byn(Byn byn) {
        if (byn == null) {
            throw new NullPointerException();
        }
        this.value = byn.value;
    }

    public Byn add(Byn byn) {
        return new Byn(value + byn.value);
    }

    public int getRubs() {
        return value / Constants.HUNDRED_FOR_BYN_RESULT;
    }

    public int getCoins() {
        return value;
    }

    public Byn diff(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn mul(int a) {
        return new Byn(value * a);
    }

    @Override
    public String toString() {
        return String.format(Constants.CONVERT_FORMAT, value / Constants.HUNDRED_FOR_BYN_RESULT,
                value % Constants.HUNDRED_FOR_BYN_RESULT);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Byn byn = (Byn) obj;
        return value == byn.value;
    }

    @Override
    public int hashCode() {
        return value + Constants.NUMBER_TO_HASH_BYN;
    }

    @Override
    public int compareTo(Byn byn) {
        return value - byn.value;
    }

}
