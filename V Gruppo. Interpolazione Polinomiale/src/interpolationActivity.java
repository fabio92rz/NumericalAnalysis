/**
 * Created by fabio on 23/05/2016.
 */

import Jama.Matrix;

public class interpolationActivity {
    public static double[] findPolynomialFactors (double[] x, double[] y)
            throws RuntimeException
    {
        int n = x.length;

        double[][] data = new double[n][n];
        double[]   rhs  = new double[n];

        for (int i = 0; i < n; i++) {
            double v = 1;
            for (int j = 0; j < n; j++) {
                data[i][n-j-1] = v;
                v *= x[i];
            }

            rhs[i] = y[i];
        }

        // Solve m * s = b

        Matrix m = new Matrix (data);
        Matrix b = new Matrix (rhs, n);

        //lel

        Matrix s = m.solve (b);

        return s.getRowPackedCopy();
    }


    public static void main (String args[])
    {
        double x[] = {2.0, 1.0, 3.0};
        double y[] = {3.0, 4.0, 7.0};

        double f[] = interpolationActivity.findPolynomialFactors (x, y);

        for (int i = 0; i < 3; i++)
            System.out.println (f[i]);
    }
}
