import by.epam.lab.factories.ResultFactory;

import static by.epam.lab.utils.Constants.*;

public class RunnerInt {
    public static void main(String[] args) {
        ResultFactory resultFactory = new ResultFactory();
        RunnerLogic.getPrintLogic(FILE_NAME_FOR_TASK1, resultFactory);
    }
}
