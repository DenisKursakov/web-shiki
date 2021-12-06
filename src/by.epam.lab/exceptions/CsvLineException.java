package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.Causes;

public class CsvLineException extends Exception{
    private String csvLine;
    private Causes cause;
    public CsvLineException(String csvLine, Causes cause) {
        this.csvLine = csvLine;
        this.cause = cause;
    }

    public CsvLineException (String csvLine, String wrongValue){

    }

    public String getCsvLine() {
        return csvLine;
    }

    public void setCsvLine(String csvLine) {
        this.csvLine = csvLine;
    }



    @Override
    public String toString() {
        return csvLine + Constants.ARROW + cause;
    }
}
