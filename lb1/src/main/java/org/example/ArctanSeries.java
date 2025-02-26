package org.example;

public class ArctanSeries {
    public static double arctan(double x, int terms) {
        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException("The decomposition does not converge at |x| > 1 ");
        }

        double sum = 0.0;
        for (int n = 0; n < terms; n++) {
            double term = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / (2 * n + 1);
            sum += term;
        }
        return sum;
    }
}