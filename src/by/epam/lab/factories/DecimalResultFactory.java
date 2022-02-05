package by.epam.lab.factories;

import by.epam.lab.beans.DecimalResult;
import by.epam.lab.beans.Result;
import by.epam.lab.beans.resultsImp.ResultImplXml;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.utils.Constants;

import java.sql.Date;

public class DecimalResultFactory extends ResultFactory {
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new DecimalResult(login, test, date, mark);
    }

    public Result getResultFromFactory(String login, String test, String date, String mark) {
        return new DecimalResult(login, test, date, mark);
    }

    public ResultDao getDaoFromFactory(String fileName) throws SourceException {
        return new ResultImplXml(fileName, this);
    }

    public double getScaledMark(double mark) {
        return mark / DecimalResult.FACTOR;
    }
}
