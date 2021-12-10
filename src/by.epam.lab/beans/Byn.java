package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.Converter;
import by.epam.lab.enums.RoundMethod;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
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

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value * k, d));

    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value, d));

    }

    public Byn divide(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value / k, d));
    }

    public Byn add(Byn byn) {
        return new Byn(value + byn.value);
    }

    public int getRubs() {
        return value / Constants.HUNDRED_FOR_BYN_RESULT;
    }

    public Byn diff(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn mul(int a) {
        return new Byn(value * a);
    }

    @Override
    public String toString() {
        return Converter.convert(value);
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
    public int compareTo(Byn byn) {
        return value - byn.value;
    }

}
