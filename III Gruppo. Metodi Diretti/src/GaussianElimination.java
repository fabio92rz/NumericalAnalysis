
import java.util.Scanner;


public class GaussianElimination

{

    public void solve(double[][] A, double[] B) {

        int N = B.length;

        for (int k = 0; k < N; k++)

        {

            //trovo il pivot
            int max = k;

            for (int i = k + 1; i < N; i++)

                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))

                    max = i;


            //Scambio le righe

            double[] temp = A[k];

            A[k] = A[max];

            A[max] = temp;

            //Scambio i giusti valori
            double t = B[k];

            B[k] = B[max];

            B[max] = t;


            //Pivot tra a e b

            for (int i = k + 1; i < N; i++)

            {

                double factor = A[i][k] / A[k][k];

                B[i] -= factor * B[k];

                for (int j = k; j < N; j++)

                    A[i][j] -= factor * A[k][j];

            }

        }
        /** Risostituisco  **/

        double[] solution = new double[N];

        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;

            for (int j = i + 1; j < N; j++)

                sum += A[i][j] * solution[j];

            solution[i] = (B[i] - sum) / A[i][i];

        }

        //Stampo la soluzione

        printSolution(solution);

    }


    public void printSolution(double[] sol)

    {

        int N = sol.length;

        System.out.println("\nSolutione : ");

        for (int i = 0; i < N; i++)

            System.out.printf("%.3f ", sol[i]);

        System.out.println();

    }

    /** Funzione principale **/

    public static void main(String[] args)

    {

        Scanner scan = new Scanner(System.in);
        System.out.println("Algoritmo di Gauss per sistemi lineari\n");


        GaussianElimination ge = new GaussianElimination();


        System.out.println("\nInserire numero di variabili");

        int N = scan.nextInt();


        double[] B = new double[N];

        double[][] A = new double[N][N];


        System.out.println("\nInserire " + N + " coefficienti dell'equazione ");

        for (int i = 0; i < N; i++)

            for (int j = 0; j < N; j++)

                A[i][j] = scan.nextDouble();

        System.out.println("\nInserisci  " + N + " soluzioni");

        for (int i = 0; i < N; i++)

            B[i] = scan.nextDouble();


        ge.solve(A, B);

    }

}


