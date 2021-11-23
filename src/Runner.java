import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        String result = "result(";
        double sumOfElements = 0;
        int errorLinesCount = 0;
        final String INPUT_CSV = "src/in.csv";
        final String PLUS = " + ";
        final String MINUS = " - ";
        try (Scanner scanner = new Scanner(new FileReader(INPUT_CSV))) {
            scanner.useLocale(Locale.ENGLISH);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] st = line.split(";");
                try {
                    String element = st[Integer.parseInt(st[0])];
                    double currentElement = Double.parseDouble(element);
                    sumOfElements += currentElement;
                    if(result.equals("result(")){
                        result += currentElement;
                        continue;
                    }
                    if(!element.contains("-")){
                        result += PLUS + currentElement;
                    } else {
                        element = MINUS + String.valueOf(currentElement).substring(1);
                        result += element;
                    }

                } catch ( IndexOutOfBoundsException | NumberFormatException e0) {
                    errorLinesCount++;
                }
            }

        } catch (FileNotFoundException e1) {
            System.out.println("Required element is not found");
        }
        System.out.println(result + ") = " + sumOfElements + "\n" + "Error-lines = " + errorLinesCount);
    }
}
