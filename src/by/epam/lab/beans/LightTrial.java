package by.epam.lab.beans;

public class LightTrial extends Trial {
    private static final int POINTS_FOR_TEST1 = 57;
    private static final int POINTS_FOR_TEST2 = 78;

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
        return getFirstTestMark() >= POINTS_FOR_TEST1 && getSecondTestMark() >= POINTS_FOR_TEST2;
    }

    @Override
    public Trial getClone() {
        return new LightTrial(this);
    }
}
