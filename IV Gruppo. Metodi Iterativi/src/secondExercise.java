import Jama.Matrix;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.Graph;
import com.panayotis.gnuplot.plot.Plot;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Fabio on 07/06/2016.
 */
public class secondExercise {

    public static final int MAX_ITERATIONS = 10;
    private static final double EPSILON = 1e-10;
    double[] graph = new double[100];

    List<Double> prova  = new ArrayList<Double>();

    public void gaussSeidel(Matrix A, double[] b) {
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
                normInf(xNew);
                System.out.println("\nSoluzioni per GaussSeidel: X_" + (i + 1) + ": " + xNew[i]);

                count++;

                if (Math.abs(xNew[i] - xOld[i]) > EPSILON) {
                    xNew[i] = xOld[i];
                } else {
                    stop = true;
                }
            }
        } while (!stop && count <= MAX_ITERATIONS);
    }

    public void jacobi(Matrix A){

        int n = A.getRowDimension() - 1;
        int iterations = 0;
        double[] X = new double[A.getRowDimension()];
        double[] P = new double[A.getRowDimension()];

        while (true) {
            for (int i = 0; i < A.getRowDimension(); i++) {
                double sum = A.get(i, n); // b_n

                for (int j = 0; j < A.getColumnDimension(); j++)
                    if (j != i)
                        sum -= A.get(i, j)* P[j];

                X[i] = 1/A.get(i, i)* sum;
                normInf(X);

            }

            System.out.print("\nSoluzioni per Jacobi: X_" + iterations + " = {");
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

    public double normInf(double[] x) {

        double sum = 0;

        for (int i = 0; i < x.length; i++) {

            sum += Math.abs(x[i]);
            Arrays.fill(graph, (int) sum);
            prova.add(sum);

        }
        System.out.println("Errore stimato per iterazione: " + sum);
        return sum;
    }

    public static boolean makeDominant(double[][] M)
    {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];

        Arrays.fill(visited, false);

        return transformToDominant(M, 0, visited, rows);
    }

    public static boolean transformToDominant(double[][] M, int r, boolean[] V, int[] R)
    {
        int n = M.length;
        if (r == M.length) {
            double[][] T = new double[n][n+1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = M[R[i]][j];
            }

            M = T;

            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;

            double sum = 0;

            for (int j = 0; j < n; j++)
                sum += Math.abs(M[i][j]);

            if (2 * Math.abs(M[i][r]) > sum) { // diagonally dominant?
                V[i] = true;
                R[r] = i;

                if (transformToDominant(M, r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }

    public static void main(String args[]){

        secondExercise secondExercise = new secondExercise();
        secondExercise.start();
    }

    public void start(){

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

        if (!makeDominant(A)){
            System.out.println("Il sistema A non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Am, Ab);
        jacobi(Am);

        Matrix Bm = new Matrix(B);

        if (!makeDominant(B)){
            System.out.println("\nIl sistema B non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Bm, Bb);
        jacobi(Bm);

        Matrix Cm = new Matrix(C);

        if (!makeDominant(C)){
            System.out.println("\nIl sistema C non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Cm, Cb);
        jacobi(Cm);

        Matrix Dm = new Matrix(D);

        if (!makeDominant(D)){
            System.out.println("\nIl sistema D non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Dm, Db);
        jacobi(Dm);

        Matrix Em = new Matrix(E);

        gaussSeidel(Em, Eb);
        jacobi(Em);

        try {
            FileWriter out = new FileWriter("error.dat");

            for (double i : prova){

                out.write(String.format("%f\n", i));
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JavaPlot plot = new JavaPlot("C:/Program Files/gnuplot/bin/gnuplot.exe");

        plot.addPlot("[2.5:3.7]");
        plot.addPlot("C:/Users/fabio/IdeaProjects/I Gruppo. Algoritmi di Base/error.dat");

        plot.plot();


    }
}

