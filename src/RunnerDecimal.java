import by.epam.lab.factories.DecimalResultFactory;

import static by.epam.lab.utils.Constants.*;

public class RunnerDecimal {
    public static void main(String[] args) {
        DecimalResultFactory decimalResultFactory = new DecimalResultFactory();
        RunnerLogic.getPrintLogic(FILE_NAME_FOR_TASK2, decimalResultFactory);
    }
}

