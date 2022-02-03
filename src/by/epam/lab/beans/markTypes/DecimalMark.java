package by.epam.lab.beans.markTypes;

import static by.epam.lab.utils.Constants.*;

public class DecimalMark extends Mark {

    public DecimalMark(int markValue) {
        super(markValue);
    }

    @Override
    protected String getStrMark(int markValue) {
        return String.format(FORMAT_MARK, markValue / TEN_FOR_GET_INT, markValue % TEN_FOR_GET_INT);
    }
}
