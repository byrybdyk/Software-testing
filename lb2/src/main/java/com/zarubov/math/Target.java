package com.zarubov.math;

import com.zarubov.CsvWriter;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Target {
    private Cot cot;
    private Cos cos;
    private Csc csc;
    private Tan tan;
    private Log log;
    private Ln ln;

    public Double calculate(Double x, Double eps) {
        if(eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x <= 0) {
            return Math.pow((((cot.calculate(x, eps) + cos.calculate(x, eps)) + csc.calculate(x, eps)) - tan.calculate(x,eps)) - cos.calculate(x, eps), 2);
        } else {
            double log2 = log.calculate(x, 2.0, eps);
            double log3 = log.calculate(x, 3.0, eps);
            double log5 = log.calculate(x, 5.0, eps);
            double log10 = log.calculate(x, 10.0, eps);
            return ((((Math.pow(log2, 2) / ln.calculate(x, eps)) / (log5 + log10)) / log5) + ((log3 * log2) * ((log5 * log5) * (log10 * log5))));
        }
    }

    public void calculateAndSaveCsv(Double x, Double eps) {
        double result = calculate(x, eps);
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Target.csv", x, result);
    }
}
