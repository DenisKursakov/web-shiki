package by.gsu.epamlab;

public class Material {
    private final String name;
    private final double density;

    public Material() {
        this.density = 0.00;
        this.name = "";
    }

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return name + ";" + density;
    }
}
