package org.firstinspires.ftc.teamcode;

public class NoiseReducer {

    /**
     * Reduces errors in vision distance values
     *
     *
     * @param values The double values to be sorted
     * @param estdistance The estimated distance of the robot. Should be the result of previous reductions or a starting value
     * @param maxerror The maximum error to allow, raise if the robot isnt detecting changes properly, lower if there is too much noise
     * @return The average of the filtered values
     */
    public static double Reduce(double[] values, double estdistance, int maxerror) {
        double last = estdistance;
        double avg = 0;
        double avgCount = 0;
        for (double val : values) {
            double error = Math.abs(val - last);
            if(error < maxerror) {
                avg += val;
                avgCount++;
                last = val;
            }
        }
        return avg / avgCount;
    }
}
