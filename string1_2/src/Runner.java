import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        double sumOfElements = 0;
        int errorLinesCount = 0;
        final String INPUT_CSV = "src/in.csv";
        final String BEFORE_SIGN = " ";
        final String AFTER_SIGN = " ";
        final String MINUS_WITHOUT_SPACES = "-";
        final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
        final String MINUS = BEFORE_SIGN + MINUS_WITHOUT_SPACES + AFTER_SIGN;
        final String SEMICOLON = ";";
        final String EQUAL_SIGN = BEFORE_SIGN + "=" + AFTER_SIGN;
        final String LEFT_BRACKET = "(";
        final String RIGHT_BRACKET = ")";
        final String FIRST_WORD = "result" + LEFT_BRACKET;
        final String RESULT_TAIL = RIGHT_BRACKET + EQUAL_SIGN;
        final String ERROR_LINES = "error" + MINUS_WITHOUT_SPACES + "lines" + EQUAL_SIGN;
        final String FILE_IS_NOT_FOUND = "File is not found";
        final String TABULATION = "\n";
        StringBuilder strResult = new StringBuilder(FIRST_WORD);
        try (Scanner scanner = new Scanner(new FileReader(INPUT_CSV))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] stringElements = line.split(SEMICOLON);
                try {
                    String element = stringElements[Integer.parseInt(stringElements[0])];
                    double currentElement = Double.parseDouble(element);
                    sumOfElements += currentElement;
                    if (strResult.toString().equals(FIRST_WORD)) {
                        strResult.append(currentElement);
                        continue;
                    }
                    if (!element.contains(MINUS_WITHOUT_SPACES)) {
                        strResult.append(PLUS).append(currentElement);
                    } else {
                        element = MINUS + String.valueOf(currentElement).substring(1);
                        strResult.append(element);
                    }

                } catch (IndexOutOfBoundsException | NumberFormatException e0) {
                    errorLinesCount++;
                }
            }

        } catch (FileNotFoundException e1) {
            System.out.println(FILE_IS_NOT_FOUND);
        }
        System.out.println(strResult + RESULT_TAIL + sumOfElements +
                TABULATION + ERROR_LINES + errorLinesCount);
    }
}
