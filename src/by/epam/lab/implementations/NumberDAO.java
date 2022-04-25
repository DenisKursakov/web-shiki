package by.epam.lab.implementations;

import by.epam.lab.exceptions.InitRuntimeException;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface NumberDAO {
    double[] getNumbers() throws InitRuntimeException;
}
