package by.gsu.epamlab;

import java.util.Objects;

public class Byn implements Comparable<Byn> {
    private int valueInKopecks;

    public Byn() {
    }

    public Byn(int valueInKopecks) {
        this.valueInKopecks = valueInKopecks;
    }

    public int diff(int a) {
        if (valueInKopecks >= a) {
            return valueInKopecks - a;
        }
        return a - valueInKopecks;
    }

    public int increase(double a) {
        return (int) Math.round(valueInKopecks * a);
    }

    public int sum(int a) {
        return valueInKopecks + a;
    }

    public int division(int a) {
        return valueInKopecks / a;
    }

    public int increase(int a) {
        return valueInKopecks * a;
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
