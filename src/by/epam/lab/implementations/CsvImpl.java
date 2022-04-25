package by.epam.lab.implementations;

import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.utils.ConstantsJSP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CsvImpl implements NumberDAO {
    @Override
    public double[] getNumbers() {
        double[] numbers = new double[ConstantsJSP.ARRAY_LENGTH];
        try (Scanner sc = new Scanner(new FileReader(ConstantsJSP.CSV_FILE_NAME))) {
            int i = 0;
            while (sc.hasNext()) {
                numbers[i] = Double.parseDouble(sc.next());
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new InitRuntimeException(e.getMessage());
        }
        return numbers;
    }
}
