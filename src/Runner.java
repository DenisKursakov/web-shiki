import by.epam.lab.comparators.NumComparator;
import by.epam.lab.constants.Constants;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(Constants.IN_FILE_WAY_STR))) {
            Map<Integer, Integer> numLenMap = new HashMap<>();
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] coordinates = currentLine.split(Constants.REGEX_FOR_COORDINATES);
                int length = calculateLength(coordinates);
                Integer num = numLenMap.get(length);
                if (num == null) {
                    num = 0;
                }
                numLenMap.put(length, num + 1);
            }
            List<Map.Entry<Integer, Integer>> numLenList = new ArrayList<>(numLenMap.entrySet());
            Collections.sort(numLenList, new NumComparator());
            printCollection(numLenList);
        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_IS_NOT_FOUND);
        }
    }

    private static int calculateLength(String[] coordinates) {
        double x1 = Double.parseDouble(coordinates[Constants.X1_COORDINATE_ID]);
        double x2 = Double.parseDouble(coordinates[Constants.X2_COORDINATE_ID]);
        double y1 = Double.parseDouble(coordinates[Constants.Y1_COORDINATE_ID]);
        double y2 = Double.parseDouble(coordinates[Constants.Y2_COORDINATE_ID]);
        return (int) Math.round(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
    }

    private static void printCollection(List<Map.Entry<Integer, Integer>> numLenList) {
        for (Map.Entry<Integer, Integer> entry : numLenList) {
            System.out.println(entry.getKey() + Constants.SEMICOLON + entry.getValue());
        }
    }
}
