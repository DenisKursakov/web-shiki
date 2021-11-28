import java.util.*;

public class Runner {
    public static void main(String[] args) {
        final String INDEX = "index";
        final String VALUE = "value";
        final String AFTER_SIGN = " ";
        final String BEFORE_SIGN = " ";
        final String EQUAL_SIGN = AFTER_SIGN + "=" + BEFORE_SIGN;
        final String ERROR_LINES = "error-lines";
        final String SUM = "sum";
        final String KEY_VALUE_REGEX = "[1-9](\\d*)";
        final String NUMBER_REGEX = "(.*)";
        int errorLineCount = 0;
        ResourceBundle rb = ResourceBundle.getBundle("in", Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        String key;
        double sum = 0.0;
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            if (key.matches(INDEX + NUMBER_REGEX)) {
                try {
                    if (key.matches(INDEX + KEY_VALUE_REGEX) &&
                            rb.getString(key).trim().matches(KEY_VALUE_REGEX)) {
                        String i = key.substring(INDEX.length());
                        String j = rb.getString(key);
                        sum += Double.parseDouble(rb.getString(VALUE + i + j));
                    } else {
                        errorLineCount++;
                    }
                } catch (NumberFormatException | MissingResourceException e) {
                    errorLineCount++;
                }
            }
        }
        System.out.println(SUM + EQUAL_SIGN + sum);
        System.out.println(ERROR_LINES + EQUAL_SIGN + errorLineCount);
    }
}
