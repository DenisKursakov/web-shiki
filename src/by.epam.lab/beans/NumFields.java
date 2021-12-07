package by.epam.lab.beans;

public class NumFields {
    private String fieldName;
    private int wrongValue;
    private String currentLine;

    public NumFields() {

    }

    public NumFields(String fieldName, int wrongValue) {
        setFieldName(fieldName);
        setWrongValue(wrongValue);
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

    public int getWrongValue() {
        return wrongValue;
    }

    public final void setWrongValue(int wrongValue) {
        if(wrongValue == 0){
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
