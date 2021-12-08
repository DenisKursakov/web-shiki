package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.exceptions.NonPositiveArgumentException;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        if (value < 0) {
            throw new NonPositiveArgumentException(String.valueOf(value));
        }
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * Constants.HUNDRED_FOR_COINS_RESULT + coins);
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
        return value / Constants.HUNDRED_FOR_RUBS_RESULT;
    }

    public Byn diff(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn mul(int a) {
        return new Byn(value * a);
    }

    @Override
    public String toString() {
        return String.format(Constants.CONVERT_FORMAT, value / Constants.HUNDRED_FOR_RUBS_RESULT,
                value % Constants.HUNDRED_FOR_COINS_RESULT);
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
