package beans;

import constants.Constants;

public class SegmentInfo implements Comparable<SegmentInfo> {
    private int segmentsLength;
    private int numberOfSegments;

    public SegmentInfo() {
    }

    public SegmentInfo(int segmentsLength, int numberOfSegments) {
        this.segmentsLength = segmentsLength;
        this.numberOfSegments = numberOfSegments;
    }

    public int getNumberOfSegments() {
        return numberOfSegments;
    }

    public void setNumberOfSegments(int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
    }

    public int getSegmentsLength() {
        return segmentsLength;
    }

    public void setSegmentsLength(int segmentsLength) {
        this.segmentsLength = segmentsLength;
    }


    @Override
    public String toString() {
        return segmentsLength + Constants.SEMICOLON + numberOfSegments;
    }

    @Override
    public int compareTo(SegmentInfo o) {
        return segmentsLength - o.segmentsLength;
    }
}
