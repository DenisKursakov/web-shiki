package by.epam.lab.beans.resultsImp;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.utils.Constants.*;

public class ResultImplCsv implements ResultDao {
    private Scanner sc;
    private ResultFactory resultFactory;

    public ResultImplCsv(String csvName, ResultFactory resultFactory) throws SourceException {
        try {
            sc = new Scanner(new FileReader(SOURCE_DIR + csvName + FILE_EXIT_CSV));
            this.resultFactory = resultFactory;
        } catch (FileNotFoundException e) {
            throw new SourceException(e.getMessage());
        }
    }

    @Override
    public Result nextResult() {
        String[] elements = sc.nextLine().split(SEMICOLON);
        return resultFactory.getResultFromFactory(
                elements[LOGIN_ID_IN_ARRAY],
                elements[TEST_ID_IN_ARRAY],
                elements[DATE_ID_IN_ARRAY],
                elements[MARK_ID_IN_ARRAY]);
    }

    @Override
    public boolean hasNextResult() {
        return sc.hasNext();
    }

    @Override
    public void close() {
        sc.close();
    }
}
