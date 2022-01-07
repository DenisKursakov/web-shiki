package by.epam.lab.beans;


import by.epam.lab.Constants;

public class NumLen {
    private int segmentsLength;
    private int numberOfSegments;


    public NumLen(int segmentsLength, int numberOfSegments) {
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

}
