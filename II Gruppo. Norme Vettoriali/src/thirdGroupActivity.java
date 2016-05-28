/**
 * Created by fabio on 17/05/2016.
 */
public class thirdGroupActivity {

    public static void main(String[] args) {
        int N = 8;

        double sum = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        double sum4 = 0.0;
        double sum5 = 0.0;
        double sum6 = 0.0;

        double[][] A = { //Diagonale Dominante
                { 10, 1, 1, 2, 3, 4, 4, 5},
                { 0, 15, 1, 2, 3, 4, 4, 5},
                { 0, 1, 20, 2, 3, 4, 4, 5},
                { 0, 1, 1, 25, 3, 4, 4, 5},
                { 0, 1, 1, 2, 30, 4, 4, 5},
                { 0, 1, 1, 2, 3, 40, 4, 5},
                { 0, 1, 1, 2, 3, 4, 50, 5},
                { 0, 1, 1, 2, 3, 4, 4, 60},
        };

        double[][] B = { //Tridiagonali
                { 10, 1, 0, 0, 0, 0, 0, 0},
                { 2, 15, 1, 0, 0, 0, 0, 0},
                { 0, 3, 20, 2, 0, 0, 0, 0},
                { 0, 0, 1, 25, 3, 0, 0, 0},
                { 0, 0, 0, 2, 30, 4, 0, 0},
                { 0, 0, 0, 0, 3, 40, 4, 0},
                { 0, 0, 0, 0, 0, 4, 50, 5},
                { 0, 0, 0, 0, 0, 0, 4, 60},
        };

        double[][] M = invert(A);
        double[][] H = invert(B);

        // norma 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                sum += Math.abs(M[i][j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                sum2 += Math.abs(H[i][j]);
            }
        }

        //norma infinito

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {

                sum3 += Math.abs(M[i][j]);
            }
        }



        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {

                sum4 += Math.abs(H[i][j]);
            }
        }



        /* norma frobenius */

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                sum5 += (M[i][j]*M[i][j]);
            }
        }
        Math.sqrt(sum5);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                sum6 += H[i][j]*H[i][j];
            }
        }
        Math.sqrt(sum6);





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