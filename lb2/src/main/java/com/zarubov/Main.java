package com.zarubov;

import com.zarubov.math.*;

public class Main {

    public static void main(String[] args) {
        NormaliseTrigonometrical nt = new NormaliseTrigonometrical();
        Cos cos = new Cos(nt);
        Cot cot = new Cot(cos, nt);
        Csc csc = new Csc(nt, cos);
        Tan tan = new Tan(nt, cos);
        Ln ln = new Ln();
        Log log = new Log(ln);

        Target target = new Target(cot, cos, csc, tan, log, ln);
        Double[][] data = {{-1.2, 0.0001}, {-1.4, 0.0001}, {-0.2, 0.0001}, {-0.1, 0.0001}, {0.2, 0.0001}, {0.4, 0.0001}, {1.4, 0.0001}, {2.1, 0.0001}, {15.2, 0.0001}};
        for (Double[] value : data) {
            try {
                target.calculateAndSaveCsv(value[0], value[1]);
            } catch (Exception e) {
                System.out.println("Error: Target.calculate(" + value[0] + ", " + value[1] + "): " + e.getMessage());
            }
        }
    }
}