package by.epam.lab.beans.resultsImp;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class ResultImplCsv implements ResultDao {
    private Scanner sc;

    public ResultImplCsv(String csvName) {
        try {
            this.sc = new Scanner(new FileReader(csvName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    @Override
    public Result nextResult() {
        Result result = null;
        if (hasNextResult()) {
            result = new Result(sc.nextLine().split(SEMICOLON));
        }
        return result;
    }

    @Override
    public boolean hasNextResult() {
        return sc.hasNextLine();
    }

    @Override
    public void close() {
        sc.close();
    }
}
