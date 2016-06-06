import Jama.Matrix;

/**
 * Created by fabio on 17/05/2016.
 */
public class fourthGroupActivity {


    public static void main(String[] args) {

        double sum = 0;
        double sum2 = 0;
        double sum3 = 0;
        double sum4 = 0;
        double sum5 = 0;
        double sum6 = 0;


        double[][] A = {
                { 4, -1,  2 },
                { 1, 3,  1 },
                { 0, -3, 5 }
        };

        double[][] B = {
                {1, 2, 0, 0},
                {3, 4, 5, 0},
                {0, 4, 5, 6},
                {0, 0, 4, 3}
        };

        double[][] C = {
                { 4, 1,  0, 0, 0},
                { 1, 5,  3, 0, 0},
                { 0, 3, 15, 3, 0},
                { 0, 0, 5, 6, 1 },
                { 0, 0, 0, 5, 6 }
        };

        Matrix f = new Matrix(A);
        double cond = f.cond();

        System.out.println("Norma 1 = " + cond);

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {

                sum += Math.abs(A[i][j]);
            }
        }

        double[][] J = invert(A);

        for (int i = 0; i < J.length; i++) {
            for (int j = 0; j < J.length; j++) {

                sum4 += Math.abs(J[i][j]);
            }
        }

        double index = sum*sum4;

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B.length; j++) {

                sum2 += Math.abs(B[i][j]);
            }
        }

        double[][] K = invert(B);

        for (int i = 0; i < K.length; i++) {
            for (int j = 0; j < K.length; j++) {

                sum5 += Math.abs(K[i][j]);
            }
        }

        double index2 = sum2*sum5;

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {

                sum3 += Math.abs(C[i][j]);
            }
        }

        double[][] O = invert(C);

        for (int i = 0; i < O.length; i++) {
            for (int j = 0; j < O.length; j++) {

                sum6 += Math.abs(O[i][j]);
            }
        }

        double index3 = sum3*sum6;

        System.out.println("Gli indici di condizionamento sono: \n Matrice A: " +index+ "\n Matrice B: " + index2 + "\n Matrice C: " +index3 );

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

    public static double oneNorm(Matrix m) {

        double largest = 0;

        for (int i = 0; i < m.getColumnDimension(); i++) {
            double sum = 0;
            for (int j = 0; j < m.getRowDimension(); j++) {

                sum += Math.abs(m.get(j, i));
            }
            if (sum > largest) {
                largest = sum;
            }
        }
        System.out.println("Norma 1 = " + largest);
        return largest;
    }

}