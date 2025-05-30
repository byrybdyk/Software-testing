package com.zarubov.math;

import com.zarubov.CsvWriter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Csc {
    private NormaliseTrigonometrical nt;
    private Cos cos;
    public Double calculate(Double x, Double eps) {
        if (eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x.isNaN() || x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("x must be a finite number");
        }
        Double xValue = nt.normalise(x);
        double cosValue = cos.calculate(xValue, eps);
        double sinValue = Math.sqrt(1 - cosValue * cosValue);
        if (xValue > 0 && xValue < Math.PI) {
            sinValue = Math.sqrt(1 - cosValue * cosValue);
        } else {
            sinValue = -Math.sqrt(1 - cosValue * cosValue);
        }
        if (Math.abs(sinValue) < eps) {
            throw new ArithmeticException("Котангенс не определен при x = " + x);
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Csc.csv", x, 1/sinValue);
        return 1/sinValue;
    }
}
