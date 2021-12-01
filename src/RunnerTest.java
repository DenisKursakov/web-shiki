import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunnerTest {
    private static int getResult(String baseName,SumResult sumResult) throws FileNotFoundException {
        final String VALUE = "value";
        final String AFTER_SIGN = " ";
        final String BEFORE_SIGN = " ";
        final int TAIL_INDEX = 1;
        final String EQUAL_SIGN = AFTER_SIGN + "=" + BEFORE_SIGN;
        final String SUM = "sum";
        final String KEY_VALUE_REGEX = "[1-9](\\d*)";
        final String INDEX_REGEX = "index(.*)";
        int errorLineCount = 0;
        ResourceBundle rb = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
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
                        sumResult.mul(Double.parseDouble(rb.getString(valueIJ)));
                    } catch (NumberFormatException | MissingResourceException e) {
                        errorLineCount++;
                    }
                } else {
                    errorLineCount++;
                }
            }
        }
        return errorLineCount;
    }

    static class SumResult {
        private double result;

        public SumResult (){
            this(0.0);
        }
        public SumResult(double result) {
            this.result = result;
        }
        public SumResult mul(double d){
            result += d;
            return this;
        }
        public double getResult (){
            return result;
        }
        @Override
        public String toString() {
            return String.valueOf(result);
        }
    }

    @Test
    public void testMainScenarioWhenBaseNameIsIn1() throws FileNotFoundException {
        SumResult sumResult = new SumResult();
        int errorLines = getResult("in1", sumResult);
        int expectedNine = 9;
        Assert.assertEquals(expectedNine, errorLines);
        double expectedResultIn1 = 30.242;
        Assert.assertEquals(expectedResultIn1, sumResult.getResult(), 0.0001);
    }

    @Test
    public void testMainScenarioWhenBaseNameIsIn2() throws FileNotFoundException {
        SumResult sumResult = new SumResult();
        int errorLines = getResult("in2", sumResult);
        int expectedNine = 3;
        Assert.assertEquals(expectedNine, errorLines);
        double expectedResultIn1 = 8.24;
        Assert.assertEquals(expectedResultIn1, sumResult.getResult(),0.001);
    }

    @Test(expected = MissingResourceException.class)
    public void testWrongBaseNameWhenBaseNameIsIn1() throws FileNotFoundException {
        String baseName = "in5";
        getResult(baseName,null);
    }

    @Test(expected = MissingResourceException.class)
    public void testWrongBaseNameWhenBaseNameIsIn2() throws FileNotFoundException {
        String baseName = "in4";
        getResult(baseName, null);
    }
}