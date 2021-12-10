package by.epam.lab;

public class Converter {
    public static String convert(int a) {
        return String.format(Constants.CONVERT_FORMAT,
                a / Constants.HUNDRED_FOR_BYN_RESULT, a % Constants.HUNDRED_FOR_BYN_RESULT);
    }
}
