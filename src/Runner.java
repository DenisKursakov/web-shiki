import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        double sumOfElements = 0;
        int errorLinesCount = 0;
        final String INPUT_CSV = "src/in.csv";
        final String PLUS = " + ";
        final String MINUS_WITH_SPACES = " - ";
        final String MINUS = "-";
        final String SEMICOLON = ";";
        final String EQUAL_SIGN = " = ";
        final String LEFT_BRACKET = "(";
        final String RIGHT_BRACKET = ")";
        final String FIRST_WORD = "result" + LEFT_BRACKET;
        String result = FIRST_WORD;
        try (Scanner scanner = new Scanner(new FileReader(INPUT_CSV))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] st = line.split(SEMICOLON);
                try {
                    String element = st[Integer.parseInt(st[0])];
                    double currentElement = Double.parseDouble(element);
                    sumOfElements += currentElement;
                    if (result.equals(FIRST_WORD)) {
                        result += currentElement;
                        continue;
                    }
                    if (!element.contains(MINUS)) {
                        result += PLUS + currentElement;
                    } else {
                        element = MINUS_WITH_SPACES + String.valueOf(currentElement).substring(1);
                        result += element;
                    }

                } catch (IndexOutOfBoundsException | NumberFormatException e0) {
                    errorLinesCount++;
                }
            }

        } catch (FileNotFoundException e1) {
            System.out.println("File is not found");
        }
        System.out.println(result + RIGHT_BRACKET + EQUAL_SIGN + sumOfElements +
                "\n" + "error-lines" + EQUAL_SIGN + errorLinesCount);
    }
}
