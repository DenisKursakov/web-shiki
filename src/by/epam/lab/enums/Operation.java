package by.epam.lab.enums;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

public enum Operation {
    SUM(Double::sum),
    MAX(Math::max),
    MIN(Math::min),
    AVG(Double::sum);

    private DoubleBinaryOperator op;

    Operation(DoubleBinaryOperator op) {
        this.op = op;
    }

    public double getResult(double[] numbers) {
        double result = Arrays.stream(numbers).reduce(op).getAsDouble();
        if (this == AVG) {
            result /= numbers.length;
        }
        return result;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

