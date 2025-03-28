package com.zarubov.math;

import com.zarubov.CsvWriter;

public class Ln {
    public Double calculate(Double x, Double eps){
        if (eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x < 0.0) {
            throw new IllegalArgumentException("ln(x) is undefined on x < 0");
        }
        if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        double y = (x - 1) / (x + 1);
        double term = y;
        double sum = term;
        int n = 1;

        while (Math.abs(term) > eps) {
            term *= y * y;
            sum += term / (2 * n + 1);
            n++;
        }

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Ln.csv", x, 2 * sum);

        return 2 * sum;
    }
}
