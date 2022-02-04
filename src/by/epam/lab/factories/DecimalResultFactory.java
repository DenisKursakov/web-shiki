package by.epam.lab.factories;

import by.epam.lab.beans.Result;
import by.epam.lab.beans.markTypes.DecimalMark;
import by.epam.lab.beans.resultsImp.ResultImplXml;
import by.epam.lab.interfaces.ResultDao;

import java.sql.Date;

public class DecimalResultFactory extends ResultFactory {
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new Result(login, test, date, new DecimalMark(mark));
    }

    public ResultDao getDaoFromFactory(String fileName) {
        return new ResultImplXml(fileName);
    }
}
