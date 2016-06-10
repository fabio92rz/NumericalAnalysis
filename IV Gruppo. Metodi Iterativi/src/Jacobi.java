/**
 * Created by fabio on 08/06/2016.
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jacobi {

    public static final int MAX_ITERATIONS = 100;
    private double[][] M;

    public Jacobi(double [][] matrix) {
        M = matrix;
    }

    public void print()
    {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);

        n = Integer.parseInt(reader.readLine());
        M = new double[n][n+1];

        for (int i = 0; i < n; i++) {
            StringTokenizer strtk = new StringTokenizer(reader.readLine());

            while (strtk.hasMoreTokens())
                for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
                    M[i][j] = Integer.parseInt(strtk.nextToken());
        }

        Jacobi jacobi = new Jacobi(M);

        writer.println();

        jacobi.print();

        jacobi.solve();

    }
}

