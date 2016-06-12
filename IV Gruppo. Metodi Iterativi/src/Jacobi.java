/**
 * Created by fabio on 08/06/2016.
 */
/* * This class provides a simple implementation of the Jacobi method for solving
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

  $ java Jacobi < equations.txt

  If the matrix isn't diagonally dominant the program tries
  to convert it(if possible) by rearranging the rows.
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jacobi {

    public static final int MAX_ITERATIONS = 100;
    private double[][] M;

    public Jacobi(double [][] matrix) { M = matrix; }

    public void print()
    {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }

    public boolean transformToDominant(int r, boolean[] V, int[] R)
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

                if (transformToDominant(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }

    public boolean makeDominant()
    {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }


    public void solve()
    {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-15;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);
        Arrays.fill(P, 0);

        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = M[i][n]; // b_n

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * P[j];

                // Update x_i but it's no used in the next row calculation
                // but up to de next iteration of the method
                X[i] = 1/M[i][i] * sum;
            }

            System.out.print("X_" + iterations + " = {");
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
            P = (double[])X.clone();
        }
    }

    public static void main(String[] args) throws IOException
    {
        int n;
        double[][] M;
        PrintWriter writer = new PrintWriter(System.out, true);

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


        n = A.length;
        M = new double[n][n+1];

        Jacobi jacobi = new Jacobi(A);
        Jacobi jacobiB = new Jacobi(B);
        Jacobi jacobiC = new Jacobi(C);
        Jacobi jacobiD = new Jacobi(D);
        Jacobi jacobiE = new Jacobi(E);


        if (!jacobi.makeDominant()) {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        if (!jacobiB.makeDominant()) {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        if (!jacobiC.makeDominant()) {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        if (!jacobiD.makeDominant()) {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        if (!jacobiE.makeDominant()) {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        writer.println();
        jacobi.print();
        jacobi.solve();
        jacobiB.print();
        jacobiB.solve();
        jacobiC.print();
        jacobiC.solve();
        jacobiD.print();
        jacobiD.solve();
        jacobiE.print();
        jacobiE.solve();

    }
}