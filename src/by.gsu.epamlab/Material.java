package by.gsu.epamlab;

import java.util.Locale;

public enum Material {
    STEEL(7850.0),
    COPPER(8500.0);
    private final double density;

    Material() {
        this(0.0);
    }

    Material(double density) {
        this.density = density;
    }


    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT) + ";" + density;
    }
}
