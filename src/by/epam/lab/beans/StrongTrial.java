package by.epam.lab.beans;

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
        return (double) (getFirstTestMark() / 2 + getSecondTestMark())
                >= (double) getPointsForPass();
    }
}
