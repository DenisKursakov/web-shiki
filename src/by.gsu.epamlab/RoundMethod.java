package by.gsu.epamlab;

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
    protected int round (double roundedValue, int d){
        int tenPow = pow10(d);
        return (int) roundFunction(roundedValue / tenPow) * tenPow;
    }
    protected int pow10 (int d){
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
