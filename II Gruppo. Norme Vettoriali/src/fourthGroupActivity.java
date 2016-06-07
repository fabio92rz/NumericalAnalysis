import Jama.Matrix;
import com.panayotis.gnuplot.JavaPlot;


/**
 * Created by fabio on 17/05/2016.
 */
public class fourthGroupActivity {


    public static void main(String[] args) {


        double[][] A = {
                { 4, -1,  2 },
                { 1, 3,  1 },
                { 0, -3, 5 }
        };

        double[][] B = {
                {1, 2, 0, 0},
                {3, 4, 5, 0},
                {0, 4, 5, 6},
                {0, 0, 4, 3}
        };

        double[][] C = {
                { 4, 1,  0, 0, 0},
                { 1, 5,  3, 0, 0},
                { 0, 3, 15, 3, 0},
                { 0, 0, 5, 6, 1 },
                { 0, 0, 0, 5, 6 }
        };

        Matrix f = new Matrix(A);
        double cond2 = condition(f);
        double cond3 = condition(hillbertMatrix(8));
        double cond4 = condition(hillbertMatrix(6));
        double cond5 = condition(hillbertMatrix(7));

        JavaPlot p = new JavaPlot("C:/Program Files/gnuplot/bin/gnuplot.exe");

    }

    public static double condition(Matrix m){

        double cond = 0;
        double normInf = m.normInf();

        Matrix n = m.inverse();
        double normInfN = n.normInf();


        if (normInfN != 0){

            cond = normInf*normInfN;

        }

        System.out.println("Indice di condizionamento = " + cond);

        return cond;
    }

    public static Matrix hillbertMatrix(int n){

        Matrix A = new Matrix(0,0);
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