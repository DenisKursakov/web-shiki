package by.epam.lab.beans;

import by.epam.lab.utils.Constants;

public class StrongTrial extends Trial {

    public StrongTrial() {
        super();
    }

    public StrongTrial(String account, int firstTest, int secondTest) {
        super(account, firstTest, secondTest);
    }

    public StrongTrial(Trial trial) {
        super(trial);
    }

    @Override
    public boolean trialIsPassed() {
        return (double) (getFirstTestMark() / Constants.TWO_FOR_HALF_SUB + getSecondTestMark())
                >= (double) getPointsForPass();
    }

    @Override
    public Trial getClone() {
        return new StrongTrial(this);
    }
}
