package by.epam.lab.factories;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.implementations.DBImpl;
import by.epam.lab.implementations.MemoryImpl;
import by.epam.lab.implementations.NumberDAO;
import by.epam.lab.implementations.CsvImpl;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletConfig;
import java.util.List;
import java.util.function.BiFunction;

public class NumberFactory {
    public enum KindOfImpl {
        MEMORY(MemoryImpl::new),
        DB(DBImpl::new),
        CSV(CsvImpl::new);
        private final BiFunction<String, ServletConfig, NumberDAO> sourceImpl;

        KindOfImpl(BiFunction<String, ServletConfig, NumberDAO> sourceImpl) {
            this.sourceImpl = sourceImpl;
        }
    }

    public static List<Double> getResultsFromFactory(ServletConfig sc) throws InitException {
        String implStr = sc.getInitParameter(ConstantsJSP.FACTORY_NUMBER);
        String[] params = implStr.split(ConstantsJSP.DELIMITER, ConstantsJSP.SPLIT_NUMBER);
        String sourceType = params[0];
        String sourceParams = params.length > 1 ? params[1] : ConstantsJSP.EMPTY_STRING;
        return KindOfImpl.valueOf(sourceType.toUpperCase()).sourceImpl.apply(sourceParams,sc).getNumbers();
    }
}
