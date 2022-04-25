package by.epam.lab.implementations;

public class MemoryImpl implements NumberDAO {
    @Override
    public double[] getNumbers() {
        return new double[] {15.2, 11.0, 2.5, 0.5, 11.2, 16.1, 27.9, 19.2, 5.8, 14.0,
                15.3, 19.1, 21.8, 7.18, 8.24, 14.3, 2.84, 20.0, 8.24, 0.71, 5.25, 6.31,
                5.11, 19.0, 15.1};
    }
}
