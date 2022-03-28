package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

public class Trial {
    private String account;
    private int firstTestMark;
    private int secondTestMark;
    private static final int POINTS_FOR_PASS = 175;

    public Trial() {
        super();
    }

    public Trial(String account, int firstTest, int secondTest) {
        super();
        this.account = account;
        this.firstTestMark = firstTest;
        this.secondTestMark = secondTest;
    }

    public Trial(String[] elements) {
        this(elements[ID_LOGIN],
                Integer.parseInt(elements[ID_FIRST_MARK]),
                Integer.parseInt(elements[ID_SECOND_MARK]));
    }

    public String getAccount() {
        return account;
    }

    public int getFirstTestMark() {
        return firstTestMark;
    }

    public void setFirstTestMark(int firstTestMark) {
        this.firstTestMark = firstTestMark;
    }

    public int getSecondTestMark() {
        return secondTestMark;
    }

    public void setSecondTestMark(int secondTestMark) {
        this.secondTestMark = secondTestMark;
    }

    public static int getPoints() {
        return POINTS_FOR_PASS;
    }

    public boolean isPassed() {
        return firstTestMark + secondTestMark >= POINTS_FOR_PASS;
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + DELIMITER +
                account + DELIMITER + firstTestMark + DELIMITER + secondTestMark;
    }


    @Override
    public String toString() {
        return fieldsToString() + DELIMITER + isPassed();
    }
}
