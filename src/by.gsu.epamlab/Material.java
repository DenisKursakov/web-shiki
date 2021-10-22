package by.gsu.epamlab;

import java.util.Locale;

public enum Material {
    STEEL(7850.0),
    COPPER(8500.0);
    private final double density;


    Material(double density) {
        this.density = density;
    }

    public String getName(){
        return name().toLowerCase(Locale.ROOT);
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return getName() + ";" + density;
    }
}
