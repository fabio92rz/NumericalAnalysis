/**
 * Created by fabio on 22/05/2016.
 */

import Jama.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class gaussSeidelActivity {


    public static final int MAX_ITERATIONS = 10;
    private static final double EPSILON = 1e-10;

    public static void gaussSeidel(double[][] A, double[] b) {
        int count = 0;
        boolean stop = false;

        double[] xNew = new double[b.length];
        double[] xOld = new double[b.length];

        do {

            for (int i = 0; i < A.length; i++) {
                double sum = 0.0;
                double sum1 = 0.0;
                for (int j = 0; j < A.length; j++) {

                    if (j != i)
                        sum += (A[i][j] * xOld[j]);

                    sum1 += (A[i][j] * xNew[j]);
                }

                xNew[i] = (b[i] - sum - sum1) * (1 / A[i][i]);
                System.out.println("X_" + (i + 1) + ": " + xNew[i]);
                System.out.println("");
                count++;

                if (Math.abs(xNew[i] - xOld[i]) > EPSILON) {
                    xNew[i] = xOld[i];
                } else {
                    stop = true;
                }
            }
        } while (!stop && count <= MAX_ITERATIONS);
    }

    public static void main(String[] args) throws IOException {
        int n = 2;
        double[][] M = {
                {0.96326, 0.81321},
                {0.81321, 0.68654}
        };

        double sum = 0.0;
        double sum2 = 0.0;



        double[] b = {0.88821, 0.74988};
        double[] c = {0.33116, 0.7};
        double[] f = {0.1432, 0.4323};

        gaussSeidel(M, b);
        gaussSeidel(M, c);
        gaussSeidel(M, f);

        double[][] a = invert(M);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {

                sum += Math.abs(M[i][j]);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {

                sum2 += Math.abs(a[i][j]);
            }
        }

        double index = sum*sum2;

        System.out.println("Indice di condizionamento = " + index);


    }

    public static double[][] invert(double a[][])

    {

        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];

        int index[] = new int[n];

        for (int i = 0; i < n; ++i)

            b[i][i] = 1;
        gaussian(a, index);

        for (int i = 0; i < n - 1; ++i)

            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        for (int i = 0; i < n; ++i)

        {

            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];

            for (int j = n - 2; j >= 0; --j)

            {

                x[j][i] = b[index[j]][i];

                for (int k = j + 1; k < n; ++k)

                {

                    x[j][i] -= a[index[j]][k] * x[k][i];
                }

                x[j][i] /= a[index[j]][j];

            }

        }

        return x;

    }

    public static void gaussian(double a[][], int index[])

    {

        int n = index.length;

        double c[] = new double[n];

        for (int i = 0; i < n; ++i)

            index[i] = i;

        for (int i = 0; i < n; ++i)

        {

            double c1 = 0;
            for (int j = 0; j < n; ++j)

            {
                double c0 = Math.abs(a[i][j]);

                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;

        }

        int k = 0;

        for (int j = 0; j < n - 1; ++j)

        {

            double pi1 = 0;

            for (int i = j; i < n; ++i)

            {

                double pi0 = Math.abs(a[index[i]][j]);

                pi0 /= c[index[i]];

                if (pi0 > pi1)

                {

                    pi1 = pi0;

                    k = i;

                }

            }


            int itmp = index[j];

            index[j] = index[k];

            index[k] = itmp;

            for (int i = j + 1; i < n; ++i)

            {

                double pj = a[index[i]][j] / a[index[j]][j];


                a[index[i]][j] = pj;


                for (int l = j + 1; l < n; ++l)

                    a[index[i]][l] -= pj * a[index[j]][l];

            }

        }
    }


}
