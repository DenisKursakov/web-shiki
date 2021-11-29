import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] args) {
        final String VALUE = "value";
        final String AFTER_SIGN = " ";
        final String BEFORE_SIGN = " ";
        final int TAIL_INDEX = 1;
        final String EQUAL_SIGN = AFTER_SIGN + "=" + BEFORE_SIGN;
        final String ERROR_LINES = "error-lines";
        final String SUM = "sum";
        final String MISSING_RESOURCE_EXC = "Missing resource exception";
        final String BASE_NAME = "in1";
        final String KEY_VALUE_REGEX = "[1-9](\\d*)";
        final String INDEX_REGEX = "index(.*)";
        int errorLineCount = 0;
        try {
            ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME, Locale.ENGLISH);
            Enumeration<String> keys = rb.getKeys();
            String key;
            double sum = 0.0;
            while (keys.hasMoreElements()) {
                key = keys.nextElement();
                Pattern pattern = Pattern.compile(INDEX_REGEX);
                Matcher keyMatcher = pattern.matcher(key);
                if (keyMatcher.matches()) {
                    String i = keyMatcher.group(TAIL_INDEX);
                    String j = rb.getString(key).trim();
                    Pattern patternIJ = Pattern.compile(KEY_VALUE_REGEX);
                    Matcher iMatcher = patternIJ.matcher(i);
                    Matcher jMatcher = patternIJ.matcher(j);
                    if (iMatcher.matches() && jMatcher.matches()) {
                        String valueIJ = VALUE + i + j;
                        try {
                            sum += Double.parseDouble(rb.getString(valueIJ));
                        } catch (NumberFormatException | MissingResourceException e) {
                            errorLineCount++;
                        }
                    } else {
                        errorLineCount++;
                    }
                }
            }
            System.out.println(SUM + EQUAL_SIGN + sum);
            System.out.println(ERROR_LINES + EQUAL_SIGN + errorLineCount);
        } catch (MissingResourceException e1) {
            System.out.println(MISSING_RESOURCE_EXC);
        }
    }
}