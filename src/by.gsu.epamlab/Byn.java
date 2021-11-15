package by.gsu.epamlab;


public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn add(Byn byn) {
        return new Byn(value + byn.value);
    }

    public int getRubs() {
        return value / 100;
    }


    public Byn mul(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value * k, d));

    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value, d));

    }

    public Byn diff(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn mul(int a) {
        return new Byn(value * a);
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", value / 100, value % 100);
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
        return byn.value - value;
    }

}