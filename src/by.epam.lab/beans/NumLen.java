package by.epam.lab.beans;

import by.epam.lab.constants.Constants;

import java.util.Objects;

public class NumLen {
    private final int segmentsLength;
    private int numberOfSegments;


    public NumLen(int segmentsLength) {
        this.segmentsLength = segmentsLength;
        numberOfSegments = 1;
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
        numLen.numberOfSegments++;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + segmentsLength;
        return result;
    }
}
