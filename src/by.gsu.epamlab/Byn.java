package by.gsu.epamlab;


public class Byn implements Comparable<Byn> {
    private int valueInKopecks;

    public Byn() {
    }

    public Byn(int valueInKopecks) {
        this.valueInKopecks = valueInKopecks;
    }

    public Byn diff(Byn byn) {
        this.valueInKopecks = valueInKopecks - byn.valueInKopecks;
        return this;
    }

    public Byn increase(double a) {
        this.valueInKopecks = (int) Math.round(valueInKopecks * a);
        return this;
    }

    public Byn increase(int a) {
        this.valueInKopecks = valueInKopecks * a;
        return this;
    }

    public Byn increase(Byn byn) {
        this.valueInKopecks = valueInKopecks * byn.valueInKopecks;
        return this;
    }

    public Byn sum(Byn byn) {
        this.valueInKopecks = valueInKopecks + byn.valueInKopecks;
        return this;
    }

    public Byn division(Byn byn) {
        this.valueInKopecks = valueInKopecks / byn.valueInKopecks;
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