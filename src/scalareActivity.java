import java.util.Locale;
import java.util.Scanner;

/**
 * Created by fabio on 09/05/2016.
 */
public class scalareActivity {
    public static void main(String arg[]) {//inizio main

        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        final int dimarray = 10;
        int n;
        double x[], y[], prod ;
        x = new double[dimarray];
        y = new double[dimarray];

        System.out.println("introdurre il numero di elementi" +
                " maggiore di 0 e minore di " + x.length );
        n = in.nextInt();

        System.out.println("introdurre le n = " + n + " componenti " +
                " del primo array");
        for(int i = 1; i<= n ; i=i+1)
            x[i] = in.nextDouble();

        System.out.println("introdurre le n = " + n + " componenti " +
                " del secondo array");
        for(int i = 1; i<= n ; i=i+1)
            y[i] = in.nextDouble();

        System.out.println("primo array");

        for(int i = 1; i <= n ; i=i+1) {

            System.out.print(x[i] + "\t");
            if (i % 5 == 0 )
                System.out.println();
        }
        System.out.println();

        System.out.println("secondo array");

        for(int i = 1; i <= n ; i=i+1) {
            System.out.print(y[i] + "\t");
            if (i % 5 == 0 )
                System.out.println();
        }
        System.out.println();

        if(n>=1){

            prod=0;
            for(int i = 1; i<= n ; i=i+1)
                prod = prod + x[i]*y[i];

            System.out.println(" prodotto scalare = " + prod );

        }
        else System.out.println("impossibile eseguire i calcoli"
                + " non ci sono elementi nell'array");

    }
}