import beans.SegmentInfo;
import constants.Constants;

import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(Constants.IN_FILE_WAY_STR))) {
            TreeSet<SegmentInfo> segmentsSet = new TreeSet<>();
            Set<SegmentInfo> resultSet = new TreeSet<>(new Comparator<>() {
                @Override
                public int compare(SegmentInfo o1, SegmentInfo o2) {
                    return o2.getNumberOfSegments() - o1.getNumberOfSegments();
                }
            });
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] firstCoordinates = currentLine.substring(
                        currentLine.indexOf(Constants.LEFT_BRACKET) + 1,
                        currentLine.indexOf(Constants.RIGHT_BRACKET)).split(Constants.SEMICOLON);
                String[] secondCoordinates = currentLine.substring(
                        currentLine.lastIndexOf(Constants.LEFT_BRACKET) + 1,
                        currentLine.lastIndexOf(Constants.RIGHT_BRACKET)).split(Constants.SEMICOLON);

                int length = (int) Math.round(Math.sqrt(getSquareDiffResult(firstCoordinates[0],
                        secondCoordinates[0]) + getSquareDiffResult(
                        firstCoordinates[1], secondCoordinates[1])));
                SegmentInfo currentSegment = new SegmentInfo(length, 1);
                SegmentInfo ceilElement = segmentsSet.ceiling(currentSegment);
                SegmentInfo floorElement = segmentsSet.floor(currentSegment);
                SegmentInfo requiredElement = (ceilElement == floorElement) ? floorElement : null;
                if (requiredElement != null) {
                    requiredElement.setNumberOfSegments(requiredElement.getNumberOfSegments() + 1);
                } else {
                    segmentsSet.add(currentSegment);
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

    private static double getSquareDiffResult(String s1, String s2) {
        double diff = Double.parseDouble(s2) - Double.parseDouble(s1);
        diff *= diff;
        return diff;
    }
}
