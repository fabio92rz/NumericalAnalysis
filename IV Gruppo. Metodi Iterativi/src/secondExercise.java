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

    public void jacobi(Matrix A, double[] b){

        int n = A.getRowDimension() - 1;
        int iterations = 0;
        double p = 0.0;
        boolean stop = false;
        double[] x_new = new double[b.length];
        double[] x_old = new double[b.length];

        do {



            for (int i = 0; i < A.getRowDimension() - 1; i++) {
                double sum = 0.0;
                for (int j = 0 ; j<A.getRowDimension() - 1; j++){

                    sum += A.get(i, j)*x_old[j];
                }
                for (int j = i + 1; j<A.getRowDimension(); j++){

                    sum += A.get(i, j)*x_old[j];
                }

                x_new[i] = (b[i] - sum)/A.get(i, i);

                p = normInf(x_new);

            }

            System.out.print("\nSoluzioni per jacobiGaussSeidel: X_" + iterations + " = {");
            for (int i = 0; i < A.getRowDimension(); i++)
                System.out.print(x_new[i] + " ");
            System.out.println("}");
            System.out.println("Errore stimato per iterazione: " + p);

            iterations++;
            if (iterations == 1) continue;

            stop = true;
            for (int i = 0; i < A.getRowDimension() && stop; i++)
                if (Math.abs(x_new[i] - x_old[i]) > EPSILON)
                    stop = false;

        }while (!stop && iterations <= MAX_ITERATIONS);
    }

    public double normInf(double[] x) {

        double sum = 0;

        for (int i = 0; i < x.length; i++) {

            sum += Math.abs(x[i]);
            Arrays.fill(graph, (int) sum);
            prova.add(sum);

        }
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
        jacobi(Am, Ab);

        Matrix Bm = new Matrix(B);

        if (!makeDominant(B)){
            System.out.println("\nIl sistema B non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Bm, Bb);
        jacobi(Bm, Bb);

        Matrix Cm = new Matrix(C);

        if (!makeDominant(C)){
            System.out.println("\nIl sistema C non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Cm, Cb);
        jacobi(Cm, Cb);

        Matrix Dm = new Matrix(D);

        if (!makeDominant(D)){
            System.out.println("\nIl sistema D non è diagonalmente dominante, il metodo non garantisce convergenza");
        }
        gaussSeidel(Dm, Db);
        jacobi(Dm, Db);

        Matrix Em = new Matrix(E);

        gaussSeidel(Em, Eb);
        jacobi(Em, Eb);

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

