package by.epam.lab.comparators;

import by.epam.lab.beans.NumLen;

import java.util.Comparator;

public class NumComparator implements Comparator<NumLen> {
    @Override
    public int compare(NumLen o1, NumLen o2) {
        return o2.getNumberOfSegments() - o1.getNumberOfSegments();
    }
}
