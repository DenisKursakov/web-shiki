import by.epam.lab.beans.NumLen;
import by.epam.lab.comparators.NumComparator;
import by.epam.lab.constants.Constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(Constants.IN_FILE_WAY_STR))) {
            Set<NumLen> segmentsSet = new HashSet<>();
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] coordinates = currentLine.split(Constants.REGEX_FOR_COORDINATES);
                int length = calculateLength(coordinates);
                segmentsSet.add(new NumLen(length));
            }
            List<NumLen> segmentsList = new ArrayList<>(segmentsSet);
            Collections.sort(segmentsList, new NumComparator());
            for (NumLen numLen : segmentsList) {
                System.out.println(numLen);
            }
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
}
