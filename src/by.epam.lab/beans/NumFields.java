package by.epam.lab.beans;

public class NumFields {
    private String fieldName;
    private String wrongValue;
    private String currentLine;

    public NumFields() {

    }

    public NumFields(String fieldName, String wrongValue, String currentLine) {
        setFieldName(fieldName);
        setWrongValue(wrongValue);
        setCurrentLine(currentLine);
    }

    public String getFieldName() {
        return fieldName;
    }

    public final void setFieldName(String fieldName) {
        if(fieldName == null){
            throw new NullPointerException();
        }
        this.fieldName = fieldName;
    }

    public String getWrongValue() {
        return wrongValue;
    }

    public final void setWrongValue(String wrongValue) {
        if(wrongValue == null){
            throw new NullPointerException();
        }
        this.wrongValue = wrongValue;
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public final void setCurrentLine(String currentLine) {
        if(currentLine == null){
            throw new NullPointerException();
        }
        this.currentLine = currentLine;
    }

    @Override
    public String toString() {
        return fieldName + ";" + wrongValue;
    }
}
