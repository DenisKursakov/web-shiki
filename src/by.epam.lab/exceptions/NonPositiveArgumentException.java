package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.NumFields;

public class NonPositiveArgumentException extends IllegalArgumentException {
    private final NumFields numFields;
    public NonPositiveArgumentException(NumFields numFields){
        this.numFields = numFields;
    }

    @Override
    public String toString() {
        return numFields.getCurrentLine() + Constants.ARROW + " non positive number " +
                numFields.getWrongValue() + " in " + numFields.getFieldName();
    }
}
