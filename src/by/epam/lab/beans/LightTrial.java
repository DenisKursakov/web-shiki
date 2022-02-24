package by.epam.lab.beans;

public class LightTrial extends Trial {
    private static final int pointsForTest1 = 57;
    private static final int pointsForTest2 = 78;

    public LightTrial() {
        super();
    }

    public LightTrial(Trial trial) {
        super(trial);
    }

    public LightTrial(String account, int firstTest, int secondTest) {
        super(account, firstTest, secondTest);
    }

    @Override
    public boolean trialIsPassed() {
        return getFirstTestMark() >= pointsForTest1 && getSecondTestMark() >= pointsForTest2;
    }

}
