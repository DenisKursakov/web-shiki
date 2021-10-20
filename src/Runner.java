import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {
    public static void main(String[] args) {
        Subject wire = new Subject("wire", Material.STEEL, 0.03);
        System.out.println(wire);
        wire.setMaterial(Material.COPPER);
        System.out.printf("Wire mass is: %.1f kg", wire.getMass());

    }
}
