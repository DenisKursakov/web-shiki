package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

public class ExtraTrial extends Trial {
    private int thirdTestMark;

    public ExtraTrial() {
        super();
    }

    public ExtraTrial(String account, int firstTest, int secondTest, int thirdTestMark) {
        super(account, firstTest, secondTest);
        this.thirdTestMark = thirdTestMark;
    }

    public ExtraTrial(ExtraTrial extraTrial) {
        this(extraTrial.getAccount(),extraTrial.getFirstTestMark(),
                extraTrial.getSecondTestMark(), extraTrial.getThirdTestMark());
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
        return new ExtraTrial(this);
    }

    @Override
    public void clearAllMarks() {
        super.clearAllMarks();
        thirdTestMark = 0;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + DELIMITER + thirdTestMark;
    }

}
