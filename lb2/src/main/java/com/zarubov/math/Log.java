package com.zarubov.math;

import com.zarubov.CsvWriter;

public class Log {
    public Double calculate(Double x, Double base, Double eps) {
        if (eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x < 0.0) {
            throw new IllegalArgumentException("log(x,base) is undefined on x < 0");
        }
        if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        if (base <= 1.0 || base == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("Base must be greater than 1 and finite");
        }

        double lnX = new Ln().calculate(x, eps);
        double lnBase = new Ln().calculate(base, eps);

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveLogToCsv("Log.csv", x,base, lnX / lnBase);

        return lnX / lnBase;
    }
}
