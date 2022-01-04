package by.epam.lab.comparators;
import java.util.Comparator;
import java.util.Map;

public class NumComparator implements Comparator<Map.Entry<Integer, Integer>> {
    @Override
    public int compare(Map.Entry<Integer, Integer> map1, Map.Entry<Integer, Integer> map2) {
        return map2.getValue() - map1.getValue();
    }
}
