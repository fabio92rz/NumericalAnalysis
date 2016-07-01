import Jama.Matrix;
import org.apache.commons.math3.complex.*;

/**
 * Created by fabio on 17/05/2016.
 */
public class thirdGroupActivity {

    /**
     * Classe per il calcolo delle norme matriciali
     */

    public static void main(String[] args) {
        int N = 8;

        double[][] A = { //Diagonale Dominante
                {10, 1, 1, 2, 3, 4, 4, 5},
                {0, 15, 1, 2, 3, 4, 4, 5},
                {0, 1, 20, 2, 3, 4, 4, 5},
                {0, 1, 1, 25, 3, 4, 4, 5},
                {0, 1, 1, 2, 30, 4, 4, 5},
                {0, 1, 1, 2, 3, 40, 4, 5},
                {0, 1, 1, 2, 3, 4, 50, 5},
                {0, 1, 1, 2, 3, 4, 4, 60},
        };

        double[][] B = { //Tridiagonali
                {10, 1, 0, 0, 0, 0, 0, 0},
                {2, 15, 1, 0, 0, 0, 0, 0},
                {0, 3, 20, 2, 0, 0, 0, 0},
                {0, 0, 1, 25, 3, 0, 0, 0},
                {0, 0, 0, 2, 30, 4, 0, 0},
                {0, 0, 0, 0, 3, 40, 4, 0},
                {0, 0, 0, 0, 0, 4, 50, 5},
                {0, 0, 0, 0, 0, 0, 4, 60},
        };


        Matrix M = new Matrix(A);
        Matrix L = new Matrix(B);

        double norm1M = oneNorm(M);
        double norm1L = oneNorm(L);
        double normFrobM = frobeniusNorm(M);
        double normFrobL = frobeniusNorm(L);
        double normInfM = infinityNorm(M);
        double normInfL = infinityNorm(L);

    }


    /**
     * Calcolo della norma 1 delle matrici
     */
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

    /**
     * Calcolo della norma 2 delle matrici
     */
    public static double pnorm(Matrix m, double p) {

        if (p == 1) {

            return oneNorm(m);
        }

        double sum = 0;

        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                if (p == 2) {

                    Complex x = new Complex(m.get(i, j));
                    sum += Math.pow(x.getReal(), p) + Math.pow(x.getImaginary(), p);

                } else {
                    sum += Math.pow(Math.abs(m.get(i, j)), p);
                }
            }
        }
        System.out.println("Norma 2 = " + Math.pow(sum, 1.0 / p));
        return Math.pow(sum, 1.0 / p);
    }

    /**
     * Calcolo della norma di Frobenius
     */
    public static double frobeniusNorm(Matrix m) {

        return pnorm(m, 2);
    }

    /**
     * Calcolo norma infinito di una matrice
     */
    public static double infinityNorm(Matrix m) {

        double largest = 0;

        for (int i = 0; i < m.getRowDimension(); i++) {
            double sum = 0;
            for (int j = 0; j < m.getColumnDimension(); j++) {
                sum += Math.abs(m.get(i, j));
            }
            if (sum > largest) {
                largest = sum;
            }
        }
        System.out.println("Norma Infinito = " + largest);
        return largest;
    }
}