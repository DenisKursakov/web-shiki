import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {
    public static void main(String[] args) {
        final Subject wire = new Subject("wire", new Material("steel", 7850.0), 0.03);
        System.out.println(wire);
        wire.setMaterial(new Material("copper", 8500.0));
        System.out.printf("Wire mass is: %.1f kg", wire.getMass());

    }
}
