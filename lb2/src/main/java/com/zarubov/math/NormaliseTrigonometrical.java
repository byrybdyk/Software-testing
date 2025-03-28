package com.zarubov.math;

public class NormaliseTrigonometrical {
    public Double normalise(Double x) {
        double result = x % (2 * Math.PI);
        if (result <= -Math.PI) {
            result += 2 * Math.PI;
        } else if (result > Math.PI) {
            result -= 2 * Math.PI;
        }
        return result;
    }
}
