package by.epam.lab.implementations;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletConfig;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvImpl implements NumberDAO {
    private final String cvsName;

    public CsvImpl(String cvsName, ServletConfig sc) {
        this.cvsName = sc.getServletContext().getRealPath(ConstantsJSP.WEB_PATH + cvsName);
    }

    @Override
    public List<Double> getNumbers() throws InitException {
        try (Scanner sc = new Scanner(new FileReader(cvsName))) {
            return Arrays.stream(sc.next().split(ConstantsJSP.DELIMITER))
                    .map(Double::parseDouble)
                    .filter(result -> result > ConstantsJSP.MIN_VALUE && result < ConstantsJSP.MAX_VALUE)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new InitException(ConstantsJSP.INIT_ERR + cvsName);
        } catch (NumberFormatException e) {
            throw new InitException(ConstantsJSP.INIT_ERR + ConstantsJSP.WRONG_PARSE_MESSAGE);
        }
    }
}
