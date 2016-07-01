import Jama.Matrix;
import com.panayotis.gnuplot.JavaPlot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fabio on 17/05/2016.
 */
public class fourthGroupActivity {
    public static void main(String[] args) {

        List<Double> graph = new ArrayList<>();

        /** Ciclo per il calcolo dell'indice di condizionamento della matrice di Hillbert da N = 2 */
        for (int n = 2; n <= 10; n++) {

            double condition = condition(hillbertMatrix(n));
            graph.add(condition);
        }

        /** Salvataggio del file conditio.dat, necessario per il plotting */
        try {
            FileWriter out = new FileWriter("condition.dat");

            for (double i : graph) {

                out.write(String.format("%f\n", i));
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo per il caloco dell'indice di condizionamento
     */

    public static double condition(Matrix m) {

        double cond = 0;
        double normInf = m.normInf();

        Matrix n = m.inverse();
        double normInfN = n.normInf();


        if (normInfN != 0) {

            cond = normInf * normInfN;

        }

        System.out.println("Indice di condizionamento = " + cond);

        return cond;
    }

    /**
     * Metodo per la creazine della Matrice di Hillbert di ordine N
     */
    public static Matrix hillbertMatrix(int n) {

        Matrix A = new Matrix(0, 0);
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = 1.0 / (i + j + 1);
                A = new Matrix(a);
            }
        }
        return A;
    }
}