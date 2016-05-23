import Jama.Matrix;

import java.util.Scanner;

/**
 * Created by fabio on 10/05/2016.
 */

public class hilbertActivity {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N;

        System.out.print("Inserisci la dimensione della matrice di Hilbert: ");
        N = input.nextInt();

        double[][] a = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = 1.0 / (i + j + 1);
        Matrix A = new Matrix(a);
        Matrix B = A.inverse();
        Matrix I = Matrix.identity(N, N);

        if (N < 7) A.print(8, 6);
        System.out.println("Numero di condizione = " + A.cond());
        System.out.println("Errore = " + A.times(B).minus(I).normInf());
    }

}
