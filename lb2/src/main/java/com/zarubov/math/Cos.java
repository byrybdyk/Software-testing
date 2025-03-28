package com.zarubov.math;

import com.zarubov.CsvWriter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cos {
    private NormaliseTrigonometrical nt;
    public  Double calculate(Double x, Double eps) {
        if (eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x.isNaN() || x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("x must be a finite number");
        }

        x = nt.normalise(x);

        double term = 1.0;
        double sum = term;
        int n = 1;

        while (Math.abs(term) > eps) {
            term *= -x * x / ((2 * n - 1) * (2 * n));
            sum += term;
            n++;
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Cos.csv", x, sum);
        return sum;
    }
}
