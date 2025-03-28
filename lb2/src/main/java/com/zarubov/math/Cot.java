package com.zarubov.math;

import com.zarubov.CsvWriter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cot {
    private Cos cos;
    private NormaliseTrigonometrical nt = new NormaliseTrigonometrical();

    public Double calculate(Double x, Double eps) {
        if (eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x.isNaN() || x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("x must be a finite number");
        }
        Double xValue = nt.normalise(x);
        double cosValue = cos.calculate(xValue, eps);  // Теперь используем экземпляр Cos
        double sinValue = Math.sqrt(1 - cosValue * cosValue);
        if (Math.abs(sinValue) < eps) {
            throw new ArithmeticException("Котангенс не определен при x = " + x);
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Cot.csv", x, cosValue / sinValue);
        return cosValue / sinValue;
    }
}
