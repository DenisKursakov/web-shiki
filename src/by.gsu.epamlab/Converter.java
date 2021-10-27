package by.gsu.epamlab;

public class Converter {
    public static String convert(int a) {
        return String.format("%d.%02d", a / 100, a % 100);
    }
}
