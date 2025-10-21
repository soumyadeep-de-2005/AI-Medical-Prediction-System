package com.medpred.service;
import com.medpred.model.Patient;

public class PredictionService {
    // Example weights for features [age, sex, bmi, glucose, bp]
    private static final double[] WEIGHTS = {0.05, 0.5, 0.1, 0.03, 0.02};
    private static final double BIAS = -0.5;

    public double predictRisk(Patient p) {
        double[] x = {
            p.getAge(),
            "F".equalsIgnoreCase(p.getSex()) ? 1.0 : 0.0,
            p.getBmi(),
            p.getGlucose(),
            p.getBloodPressure()
        };
        double z = BIAS;
        for (int i = 0; i < WEIGHTS.length; i++) {
            z += WEIGHTS[i] * x[i];
        }
        z=z-8;
        return 1.0 / (1.0 + Math.exp(-z));
    }
}
