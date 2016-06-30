/**
 * Created by fabio on 08/06/2016.
 */
/* * This class provides a simple implementation of the jacobiGaussSeidel method for solving
 * systems of linear equations. */

/*
  How to use:
  The program reads an augmented matrix from standard input,
  for example:

   3
   5 -2  3 -1
  -3  9  1  2
   2 -1 -7  3

  The number in the first line is the number of equations
  and number of variables. You can put this values in a file
  and then execute the program as follows:

  $ java jacobiGaussSeidel < equations.txt

  If the matrix isn't diagonally dominant the program tries
  to convert it(if possible) by rearranging the rows.
*/

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;
import sun.plugin.javascript.navig.Array;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**Questa classe fornisce una semplice implementazione del metodo di Jacobi per
 * risolvere sistemi di equazioni lineari.*/

/**
  Istruzioni:

  Il programma legge una matrice aumentata dall'input,
  Per esempio:

   3
   5 -2  3 -1
  -3  9  1  2
   2 -1 -7  3

  Il primo numero equivale al numero dell'equazioni e delle variabili

  Se la matrice non è diagonalmente dominante, il programma prova a convertirla
  (se possibile) riordinando le righe.
*/


public class jacobiGaussSeidel {

    public static final int MAX_ITERATIONS = 100;
    private double[][] M;
    public static List<Double> graph = new ArrayList<>();

    public jacobiGaussSeidel(double[][] matrix) {
        M = matrix;
    }


    /**Metodo per stampare la matrice**/

    public void print() {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }

    /**Metodo per transformarla in diagonale dominante**/

    public boolean transformToDominant(int r, boolean[] V, int[] R) {
        int n = M.length;
        if (r == M.length) {
            double[][] T = new double[n][n + 1];
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

            if (2 * Math.abs(M[i][r]) > sum) {
                V[i] = true;
                R[r] = i;

                if (transformToDominant(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }

    public boolean makeDominant() {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }

    /**
     * Si applica il metodo di Jacobi per trovare la soluzione di un
     * sistema lineare di equazioni rappresentato nella matrice M.
     * M è una matrice in questa forma:
     * a_11 * x_1 + a_12 * x_2 + ... + a_1n * x_n = b_1
     * a_21 * x_1 + a_22 * x_2 + ... + a_2n * x_n = b_2
     * .                 .                  .        .
     * .                 .                  .        .
     * .                 .                  .        .
     * a_n1 * x_n + a_n2 * x_2 + ... + a_nn * x_n = b_n
     */

    public void solve() {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-15;
        double[] X = new double[n];
        double[] P = new double[n];
        double[] prev = new double[n];
        double err = 0.0;
        Arrays.fill(X, 0);
        Arrays.fill(P, 0);
        Arrays.fill(prev, 0);

        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = M[i][n]; // b_n

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * P[j];
                X[i] = 1 / M[i][i] * sum;

                prev[i] = X[i];
            }

            System.out.print("\nSoluzioni per Jacobi : X_" + iterations + " = {");
            for (int i = 0; i < n; i++){
                System.out.print(X[i] + " ");
            }
            System.out.println("}");

            for (int i = 0; i<n; i++){
                System.out.print("\nVettore precedente = " + prev[i]);
            }

            iterations++;
            if (iterations == 1) continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;

            if (stop || iterations == MAX_ITERATIONS) break;
            P = (double[]) X.clone();
        }
    }

    /**
     * Si applica il metodo di Gauss Seidel per trovare la soluzione di un
     * sistema lineare di equazioni rappresentato nella matrice M.
     * M è una matrice in questa forma:
     * a_11 * x_1 + a_12 * x_2 + ... + a_1n * x_n = b_1
     * a_21 * x_1 + a_22 * x_2 + ... + a_2n * x_n = b_2
     * .                 .                  .        .
     * .                 .                  .        .
     * .                 .                  .        .
     * a_n1 * x_n + a_n2 * x_2 + ... + a_nn * x_n = b_n
     */


    public void solveGaussSeidel() {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-15;
        double[] X = new double[n];
        double[] P = new double[n];
        Arrays.fill(X, 0);
        Arrays.fill(P, 0);

        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = M[i][n]; // b_n

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * X[j];

                X[i] = 1 / M[i][i] * sum;
            }

            System.out.print("Soluzioni per Gauss Seidel: X_" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");

            iterations++;
            if (iterations == 1) continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;

            if (stop || iterations == MAX_ITERATIONS) break;
            P = (double[]) X.clone();
        }
    }

    /**Norma infinito di un vettore per calcolare l'errore relativo
     * per ogni iterezaione.*/

    public double normInf(double[] x) {

        double sum = 0;
        RealVector sum2 = MatrixUtils.createRealVector(x);

        sum = sum2.getLInfNorm();
        graph.add(sum);

        return sum;
    }

    public static void main(String[] args) throws IOException {
        int n;
        double[][] M;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);

        System.out.print("Inserisci il numero di variabili: ");

        n = Integer.parseInt(reader.readLine());

        System.out.print("Inserisci la matrice: \n");
        M = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer strtk = new StringTokenizer(reader.readLine());

            while (strtk.hasMoreTokens())
                for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
                    M[i][j] = Integer.parseInt(strtk.nextToken());
        }

        jacobiGaussSeidel matrix = new jacobiGaussSeidel(M);

        if (!matrix.makeDominant()) {
            writer.println("Il sistema non è diagonalmente dominante: " +
                    "Il metodo non garantisce convergenza.");
        }

        writer.println();
        matrix.print();
        matrix.solve();
        matrix.solveGaussSeidel();
    }
}