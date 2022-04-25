package by.epam.lab.factories;

import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.implementations.DBImpl;
import by.epam.lab.implementations.MemoryImpl;
import by.epam.lab.implementations.NumberDAO;
import by.epam.lab.implementations.CsvImpl;

public class NumberFactory {
    public enum KindOfImpl {
        MEMORY {
            @Override
            public NumberDAO getDao() {
                return new MemoryImpl();
            }
        },
        DB {
            @Override
            public NumberDAO getDao() {
                return new DBImpl();
            }
        },
        CSV {
            @Override
            public NumberDAO getDao() {
                return new CsvImpl();
            }
        };

        public abstract NumberDAO getDao();
    }

    public static double[] getResultsFromFactory(String implStr) {
        return KindOfImpl.valueOf(implStr.toUpperCase()).getDao().getNumbers();
    }
}
