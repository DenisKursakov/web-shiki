package by.epam.lab.beans;


import by.epam.lab.Constants;

public class NumLen {
    private final int segmentsLength;
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

    public void incNum() {
        numberOfSegments++;
    }

    @Override
    public String toString() {
        return segmentsLength + Constants.SEMICOLON + numberOfSegments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NumLen numLen = (NumLen) o;
        if (segmentsLength != numLen.segmentsLength)
            return false;
        numLen.incNum();
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = Constants.NUMBER_FOR_MUL_HASHCODE;
        int result = 1;
        result = PRIME * result + segmentsLength;
        return result;
    }
}
