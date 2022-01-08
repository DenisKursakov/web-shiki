package by.epam.lab.beans;

import by.epam.lab.Constants;

public class LenNum {
    private final int segmentsLength;
    private final numberOfSegments;


    public LenNum(int segmentsLength, int numberOfSegments) {
        this.segmentsLength = segmentsLength;
        this.numberOfSegments = numberOfSegments;
    }

    public int getNumberOfSegments() {
        return numberOfSegments;
    }

    public int getSegmentsLength() {
        return segmentsLength;
    }

    @Override
    public String toString() {
        return segmentsLength + Constants.SEMICOLON + numberOfSegments;
    }
}
