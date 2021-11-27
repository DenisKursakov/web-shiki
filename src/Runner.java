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
        StringBuilder strResult = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader(INPUT_CSV))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNext()) {
                String[] stringElements = scanner.nextLine().split(SEMICOLON);
                try {
                    int elementId = Integer.parseInt(stringElements[0]);
                    double currentElement = Double.parseDouble(stringElements[elementId]);
                    sumOfElements += currentElement;
                    strResult.append(currentElement < 0 ? MINUS : PLUS)
                            .append(Math.abs(currentElement));

                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    errorLinesCount++;
                }
            }
            if (strResult.length() > 0) {
                final int SIGN_LENGTH = MINUS.length();
                final char CHAR_MINUS = '-';
                String firstCharacter = strResult.substring(0, SIGN_LENGTH);
                strResult.delete(0, SIGN_LENGTH);
                if (firstCharacter.equals(MINUS)) {
                    strResult.insert(0, CHAR_MINUS);
                }
            }
            System.out.println(FIRST_WORD + strResult + RESULT_TAIL + sumOfElements +
                    TABULATION + ERROR_LINES + errorLinesCount);

        } catch (FileNotFoundException e) {
            System.out.println(FILE_IS_NOT_FOUND);
        }
    }
}
