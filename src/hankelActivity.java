import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.exception.*;
import org.apache.commons.math3.linear.*;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;


/**
 * Created by fabio on 11/05/2016.
 */
public class hankelActivity {

    public static RealMatrix createHankelMatrix(RealMatrix data, int windowSize) {

        int n = data.getRowDimension();
        int m = data.getColumnDimension();
        int k = n - windowSize + 1;

        RealMatrix res = MatrixUtils.createRealMatrix(k, m * windowSize);
        double[] buffer = {};

        for (int i = 0; i < n; ++i) {
            double[] row = data.getRow(i);
            buffer = ArrayUtils.addAll(buffer, row);

            if (i >= windowSize - 1) {
                RealMatrix mat = MatrixUtils.createRowRealMatrix(buffer);
                res.setRowMatrix(i - windowSize + 1, mat);
                buffer = ArrayUtils.subarray(buffer, m, buffer.length);
            }
        }

        return res;
    }

    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        int n, m;

        System.out.print("Inserisci numero di righe e colonne della matrice di Hankel: ");
        n = input.nextInt();
        m = input.nextInt();

    }
}


