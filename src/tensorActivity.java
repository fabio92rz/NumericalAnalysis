import java.util.Locale;
import java.util.Scanner;

import static javafx.scene.input.KeyCode.K;

/**
 * Created by fabio on 10/05/2016.
 */
public class tensorActivity {

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        final int dimarray = 10;
        int n;
        double x[], y[];
        x = new double[dimarray];
        y = new double[dimarray];

        System.out.println("introdurre il numero di elementi" +
                " maggiore di 0 e minore di " + x.length);
        n = in.nextInt();

        System.out.println("introdurre le n = " + n + " componenti " +
                " del primo array");
        for (int i = 1; i <= n; i = i + 1)
            x[i] = in.nextDouble();

        System.out.println("introdurre le n = " + n + " componenti " +
                " del secondo array");
        for (int i = 1; i <= n; i = i + 1)
            y[i] = in.nextDouble();

        System.out.println("primo array");

        for (int i = 1; i <= n; i = i + 1) {

            System.out.print(x[i] + "\t");
            if (i % 5 == 0)
                System.out.println();
        }
        System.out.println();

        System.out.println("secondo array");

        for (int i = 1; i <= n; i = i + 1) {
            System.out.print(y[i] + "\t");
            if (i % 5 == 0)
                System.out.println();
        }
        System.out.println();

        if (n>=1){

            int i;
            double prodotto[];
            prodotto = new double[n+1];

            for (i=1 ; i<=n ; i++){

                prodotto[i] = x[i]*y[i];

                System.out.println("Il prodotto tensoriale vale: " + prodotto[i]);
            }
        }
    }
}
