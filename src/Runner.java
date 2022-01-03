
import by.epam.lab.beans.NumLen;
import by.epam.lab.constants.Constants;

import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(Constants.IN_FILE_WAY_STR))) {
            Set<NumLen> segmentsSet = new HashSet<>();
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] coordinates = currentLine.split(Constants.REGEX_FOR_COORDINATES);
                int length = (int) Math.round(Math.sqrt(
                        getSquareDiffResult(
                                coordinates[Constants.X1_COORDINATE_ID],
                                coordinates[Constants.X2_COORDINATE_ID])
                                + getSquareDiffResult(
                                coordinates[Constants.Y1_COORDINATE_ID],
                                coordinates[Constants.Y2_COORDINATE_ID])));
                segmentsSet.add(new NumLen(length));
            }
            List<NumLen> segmentsList = new ArrayList<>(segmentsSet);
            Collections.sort(segmentsList, new Comparator<>() {
                @Override
                public int compare(NumLen o1, NumLen o2) {
                    return o2.getNumberOfSegments() - o1.getNumberOfSegments();
                }
            });

            for (NumLen segmentsInfo : segmentsList) {
                System.out.println(segmentsInfo);
            }
        } catch (Exception e) {
            System.out.println(Constants.FILE_IS_NOT_FOUND);
        }
    }

    private static double getSquareDiffResult(String s1, String s2) {
        double diff = Double.parseDouble(s2) - Double.parseDouble(s1);
        diff *= diff;
        return diff;
    }
}
