package by.epam.lab.factories;

import by.epam.lab.beans.Result;
import by.epam.lab.beans.markTypes.HalfMark;
import by.epam.lab.beans.resultsImp.ResultImplCsv;
import by.epam.lab.interfaces.ResultDao;

import java.sql.Date;

public class HalfFactoryResult extends ResultFactory {
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new Result(login, test, date, new HalfMark(mark));
    }

    public ResultDao getDaoFromFactory(String fileName) {
        return new ResultImplCsv(fileName);
    }
}
