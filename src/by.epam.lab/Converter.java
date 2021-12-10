package by.epam.lab;

public class Converter {
    public static String convert(int a) {
        return String.format(Constants.CONVERT_FORMAT, a / 100, a % 100);
    }
}
