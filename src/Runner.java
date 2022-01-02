import by.epam.lab.beans.SegmentInfo;
import by.epam.lab.constants.Constants;

import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(Constants.IN_FILE_WAY_STR))) {
            Set<SegmentInfo> segmentsSet = new TreeSet<>();
            Set<SegmentInfo> resultSet = new TreeSet<>(new Comparator<>() {
                @Override
                public int compare(SegmentInfo o1, SegmentInfo o2) {
                    return o2.getNumberOfSegments() - o1.getNumberOfSegments();
                }
            });
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
                SegmentInfo requiredElement = getElement(segmentsSet, length);
                if (requiredElement != null) {
                    requiredElement.setNumberOfSegments(requiredElement.getNumberOfSegments() + 1);
                } else {
                    segmentsSet.add(new SegmentInfo(length, 1));
                }
            }
            resultSet.addAll(segmentsSet);

            for (SegmentInfo segmentsInfo : resultSet) {
                System.out.println(segmentsInfo);
            }
        } catch (Exception e) {
            System.out.println(Constants.FILE_IS_NOT_FOUND);
        }
    }

    private static SegmentInfo getElement(Set<SegmentInfo> segments, int length) {
        List<SegmentInfo> currentSegmentsList = new ArrayList<>(segments);
        int requiredId = Collections.binarySearch(
                currentSegmentsList, new SegmentInfo(length, 1));
        return requiredId >= 0 ? currentSegmentsList.get(requiredId) : null;
    }

    private static double getSquareDiffResult(String s1, String s2) {
        double diff = Double.parseDouble(s2) - Double.parseDouble(s1);
        diff *= diff;
        return diff;
    }
}
