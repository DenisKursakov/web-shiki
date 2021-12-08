package by.epam.lab.exceptions;

import by.epam.lab.Constants;

public class NonPositiveArgumentException extends IllegalArgumentException {
    private final String currentLine;
    private final String wrongField;
    private final String wrongElement;

    public NonPositiveArgumentException() {
        this(null, null, null);
    }

    public NonPositiveArgumentException(String wrongField, String wrongElement) {
        this.currentLine = null;
        this.wrongField = wrongField;
        this.wrongElement = wrongElement;
    }

    public NonPositiveArgumentException(String currentLine,
                                        String wrongField, String wrongElement) {
        this.currentLine = currentLine;
        this.wrongField = wrongField;
        this.wrongElement = wrongElement;
    }

    public NonPositiveArgumentException(String wrongValue) {
        this(null, null, wrongValue);
    }

    public String getWrongField() {
        return wrongField;
    }

    public String getWrongElement() {
        return wrongElement;
    }

    @Override
    public String toString() {
        return currentLine + Constants.ARROW + Constants.NON_POSITIVE +
                wrongElement + Constants.IN + wrongField;
    }
}
