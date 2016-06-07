import Jama.Matrix;

/**
 * Created by Fabio on 07/06/2016.
 */
public class secondExercise {

    public static final int MAX_ITERATIONS = 10;
    private static final double EPSILON = 1e-10;

    public static void gaussSeidel(Matrix A, double[] b) {
        int count = 0;
        boolean stop = false;

        double[] xNew = new double[b.length];
        double[] xOld = new double[b.length];

        do {

            for (int i = 0; i < A.getRowDimension(); i++) {
                double sum = 0.0;
                double sum1 = 0.0;
                for (int j = 0; j < A.getColumnDimension(); j++) {

                    if (j != i)
                        sum += (A.get(i, j) * xOld[j]);

                    sum1 += (A.get(i, j)* xNew[j]);
                }

                xNew[i] = (b[i] - sum - sum1) * (1 / A.get(i, i));
                System.out.println("\n Soluzioni per GaussSeidel: X_" + (i + 1) + ": " + xNew[i]);
                count++;

                if (Math.abs(xNew[i] - xOld[i]) > EPSILON) {
                    xNew[i] = xOld[i];
                } else {
                    stop = true;
                }
            }
        } while (!stop && count <= MAX_ITERATIONS);
    }

    public static void jacobi(Matrix A){

        int n = 3;
        int iterations = 0;
        double[] X = new double[A.getRowDimension()]; // Approximations
        double[] P = new double[A.getRowDimension()]; // Prev

        while (true) {
            for (int i = 0; i < A.getRowDimension(); i++) {
                double sum = A.get(i, A.getRowDimension()); // b_n

                for (int j = 0; j < A.getColumnDimension(); j++)
                    if (j != i)
                        sum -= A.get(i, j)* P[j];

                X[i] = 1/A.get(i, i)* sum;
            }

            System.out.print("\n Soluzioni per Jacobi: X_" + iterations + " = {");
            for (int i = 0; i < A.getRowDimension(); i++)
                System.out.print(X[i] + " ");
            System.out.println("}");

            iterations++;
            if (iterations == 1) continue;

            boolean stop = true;
            for (int i = 0; i < A.getRowDimension() && stop; i++)
                if (Math.abs(X[i] - P[i]) > EPSILON)
                    stop = false;

            if (stop || iterations == MAX_ITERATIONS) break;
            P = (double[])X.clone();
        }
    }

    public static void main(String args[]){

        double[][] A = {
                {3, 0, 4},
                {7, 4, 2},
                {-1, -1, -2}
        };

        double[] Ab = {7, 13, -4};

        double[][] B ={
                {-3, 3, -6},
                {-4, 7, -8},
                {5, 7, -9}
        };

        double[] Bb = {-6, -6, 3};

        double[][] C = {
                {4, 1, 1},
                {2, -9, 0},
                {0, -8, -6}
        };

        double[] Cb = {6, -7, -14};

        double[][] D = {
                {7, 6, 9},
                {4, 5, -4},
                {-7, -3, 8}
        };

        double[] Db = {22, 5, -2};

        double[][] E = {
                {-4, -1, 1, 1},
                {0, -4, -1, 1},
                {-1, -1, 4, 1},
                {1, -1, 0, 4}
        };

        double[] Eb = {-3, -4, 3, 4};


        Matrix Am = new Matrix(A);
        gaussSeidel(Am, Ab);
        jacobi(Am);

        Matrix Bm = new Matrix(B);
        gaussSeidel(Bm, Bb);
        jacobi(Bm);

        Matrix Cm = new Matrix(C);
        gaussSeidel(Cm, Cb);
        jacobi(Cm);

        Matrix Dm = new Matrix(D);
        gaussSeidel(Dm, Db);
        jacobi(Dm);

        Matrix Em = new Matrix(E);
        gaussSeidel(Em, Eb);
        //jacobi(Em);

    }



}
