package by.epam.lab.enums;

import java.util.Arrays;

public enum Operation {
    SUM {
        @Override
        public double getResult(double[] numbers) {
            return Arrays.stream(numbers).sum();
        }
    },
    MAX {
        @Override
        public double getResult(double[] numbers) {
            return Arrays.stream(numbers).max().getAsDouble();
        }
    },
    MIN {
        @Override
        public double getResult(double[] numbers) {
            return Arrays.stream(numbers).min().getAsDouble();
        }
    },
    AVG {
        @Override
        public double getResult(double[] numbers) {
            return Arrays.stream(numbers).average().getAsDouble();
        }
    };

    public abstract double getResult (double[] numbers);

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

