package by.gsu.epamlab;

public class Material {
    private String name;
    private final double density;

    public Material() {
        this.density = 0.00;
    }

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public String getName() {
        return name;
    }

    public double getDENSITY() {
        return density;
    }

    @Override
    public String toString() {
        return name + ";" + density;
    }
}
