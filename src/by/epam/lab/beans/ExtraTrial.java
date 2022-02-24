package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.DELIMITER;

public class ExtraTrial extends Trial {
    private static final int pointsForPassedThirdTest = 78;
    private int thirdTestMark;

    public ExtraTrial() {
        super();
    }

    public ExtraTrial(String account, int firstTest, int secondTest, int thirdTestMark) {
        super(account, firstTest, secondTest);
        this.thirdTestMark = thirdTestMark;
    }

    public static int getPointsForPassedThirdTest() {
        return pointsForPassedThirdTest;
    }

    public int getThirdTestMark() {
        return thirdTestMark;
    }

    public void setThirdTestMark(int thirdTestMark) {
        this.thirdTestMark = thirdTestMark;
    }

    @Override
    public boolean trialIsPassed() {
        return super.trialIsPassed() && thirdTestMark >= pointsForPassedThirdTest;
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
        return super.fieldsToString() + DELIMITER + thirdTestMark;
    }

}
