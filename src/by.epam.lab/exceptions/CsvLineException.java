package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.Causes;

public class CsvLineException extends Exception {
    private final String csvLine;
    private final Causes cause;
    private final String strCause;

    public CsvLineException(String csvLine, Causes cause) {
        this.csvLine = csvLine;
        this.cause = cause;
        this.strCause = cause.toString();
    }

    public CsvLineException(String csvLine, Causes cause, String wrongFieldInfo) {
        this.csvLine = csvLine;
        this.cause = cause;
        this.strCause = cause.toString() + wrongFieldInfo;
    }


    public String getCsvLine() {
        return csvLine;
    }

    public String getStrCause() {
        return strCause;
    }

    @Override
    public String toString() {
        return csvLine + Constants.ARROW + strCause;
    }
}
