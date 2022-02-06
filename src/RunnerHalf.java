import by.epam.lab.factories.HalfFactoryResult;

import static by.epam.lab.utils.Constants.*;

public class RunnerHalf {
    public static void main(String[] args) {
        HalfFactoryResult halfFactoryResult = new HalfFactoryResult();
        RunnerLogic.getPrintLogic(FILE_NAME_FOR_TASK3, halfFactoryResult);
    }
}
