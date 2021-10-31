package by.gsu.epamlab;


public class Byn implements Comparable<Byn> {
    private int valueInKopecks;

    public Byn() {
    }
    public Byn(int valueInKopecks) {
        this.valueInKopecks = valueInKopecks;
    }
    public Byn(int rubs, int coins){
        this(Integer.parseInt(String.format("%d%d",rubs,coins)));
    }
    public Byn (Byn byn){
        this(byn.valueInKopecks);
    }

    public Byn add (Byn byn){
        valueInKopecks = byn.valueInKopecks;
        return byn;
    }
    public int getRubs(){
        return valueInKopecks/100;
    }
    public int getCoins(){
        return valueInKopecks%100;
    }
    public Byn mul(double k, RoundMethod roundMethod, int d){
        valueInKopecks = roundMethod.round(valueInKopecks * k, roundMethod, d) ;
        return this;
    }
    public Byn round (RoundMethod roundMethod, int d){
        valueInKopecks = roundMethod.round(valueInKopecks , roundMethod, d);
        return this;
    }

    public Byn diff(Byn byn) {
        valueInKopecks = valueInKopecks - byn.valueInKopecks;
        return this;
    }

    public Byn increase(double a) {
        valueInKopecks = (int) Math.round(valueInKopecks * a);
        return this;
    }

    public Byn increase(int a) {
        valueInKopecks = valueInKopecks * a;
        return this;
    }

    public Byn increase(Byn byn) {
        valueInKopecks = valueInKopecks * byn.valueInKopecks;
        return this;
    }

    public Byn sum(Byn byn) {
        valueInKopecks = valueInKopecks + byn.valueInKopecks;
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
    public enum RoundMethod {
        FLOOR {
            double roundFunction (double d){
                return Math.floor(d);
            }
        },
        ROUND {
            double roundFunction(double d) {
                return Math.round(d);
            }
        },
        CEIL {
            double roundFunction(double d) {
                return Math.ceil(d);
            }
        };
        abstract double roundFunction (double value);
        int round (double roundedValue, RoundMethod roundMethod, int d){
            int tenPow = pow10(d);
            return (int) roundMethod.roundFunction(roundedValue / tenPow) * tenPow;
        }
        int pow10 (int d){
            int required = 10;
            int[] tenPowD = {1,10,100,1000,10000,100000,1000000,10000000,100000000};
            for (int i = 0; i < tenPowD.length; i++) {
                if(i == d){
                    required = tenPowD[i];
                }
            }
            return required;
        }
    }
}