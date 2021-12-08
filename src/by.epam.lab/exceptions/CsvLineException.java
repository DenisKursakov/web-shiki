package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.Causes;

public class CsvLineException extends Exception {
    private final String csvLine;
    private final Causes cause;

    public CsvLineException(String csvLine, Causes cause) {
        this.csvLine = csvLine;
        this.cause = cause;
    }

    public String getCsvLine() {
        return csvLine;
    }


    @Override
    public String toString() {
        return csvLine + Constants.ARROW + cause;
    }
}
