package by.epam.lab.beans;

import by.epam.lab.utils.Constants;

public class ExtraTrial extends Trial {
    private static final int POINTS_FOR_PASSED_THIRD_TEST = 78;
    private int thirdTestMark;

    public ExtraTrial() {
        super();
    }

    public ExtraTrial(String account, int firstTest, int secondTest, int thirdTestMark) {
        super(account, firstTest, secondTest);
        this.thirdTestMark = thirdTestMark;
    }

    public static int getPointsForPassedThirdTest() {
        return POINTS_FOR_PASSED_THIRD_TEST;
    }

    public int getThirdTestMark() {
        return thirdTestMark;
    }

    public void setThirdTestMark(int thirdTestMark) {
        this.thirdTestMark = thirdTestMark;
    }

    @Override
    public boolean trialIsPassed() {
        return super.trialIsPassed() && thirdTestMark >= POINTS_FOR_PASSED_THIRD_TEST;
    }

    @Override
    public Trial getClone() {
        return new ExtraTrial(getAccount(),getFirstTestMark(),getSecondTestMark(),thirdTestMark);
    }

    @Override
    public void clearAllMarks() {
        super.clearAllMarks();
        setThirdTestMark(0);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + thirdTestMark;
    }

}
