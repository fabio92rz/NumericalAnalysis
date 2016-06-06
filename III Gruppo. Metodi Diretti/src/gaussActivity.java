/**
 * Created by fabio on 22/05/2016.
 */
public class gaussActivity {

    // Gaussian elimination with partial pivoting
    public static double[] lsolve(double[][] A, double[] b) {
        int N  = b.length;

        for (int p = 0; p < N; p++) {

            //Trovo il pivot e faccio lo scambio
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;


            // Pivot tra a e b
            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // Risostituisco
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }


    public static void main(String[] args) {
        int N = 6;
        double[][] A = {
                { 0, 1, 1, 3, 4, 5},
                { 2, 4, -2, 4, 5, 6},
                { 0, 3, 15, 5, 6, 7},
                { 3, 4, 5, 6, 6, 7},
                {4, 5, 6, 4, 6, 7},
                {5, 6, 7, 4, 3, 2}
        };

        double[][] Z = {
                { 0, 1, 1, 3, 4, 5},
                { 2, 4, -2, 4, 5, 6},
                { 0, 3, 4, 5, 6, 7},
                { 3, 4, 5, 6, 6, 7},
                {4, 5, 6, 4, 6, 7},
                {5, 6, 7, 4, 3, 2}
        };

        double[] er = new double[N];
        double[] b = { 4, 2, 36, 10, 12, 31};
        double[] x = lsolve(A, b);

        double[] v = lsolve(Z, b);

        for (int n = 0; n<N; n++){
            double m = (v[n] + x[n])/2;
            er[n] = Math.abs(v[n] - x[n])/m;
        }


        //Stampo i risultati
        for (int i = 0; i < N; i++) {

            System.out.println("Soluzione del sistema" + x[i]);
            System.out.println("\nErrore relativo sulla soluzione: " + er[i]);
        }

        for (int p = 0; p < N; p++) {
            System.out.println("\nSoluzione sistema perturbato" + v[p]);
        }






    }

}