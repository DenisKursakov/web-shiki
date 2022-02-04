package by.epam.lab.beans.markTypes;

import static by.epam.lab.utils.Constants.*;

public class HalfMark extends Mark {

    public HalfMark(int markValue) {
        super(markValue);
    }

    @Override
    protected String getStrMark(int markValue) {
        return markValue % TEN_FOR_GET_INT == 0 ? String.valueOf(markValue / TEN_FOR_GET_INT) :
                String.format(FORMAT_MARK, markValue / TEN_FOR_GET_INT, markValue % TEN_FOR_GET_INT);
    }
}
