import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by Fabio on 28/05/2016.
 */
public class Vector {

    private double[] data;       // array of vector's components


    // test client
    public static void main(String[] args) {
        double[] xdata = { 1.0, 2.0, 3.0, 4.0 };
        double[] ydata = { 5.0, 2.0, 4.0, 1.0 };

        double c1 = 7.0;
        double c2 = 4.0;

        double somma = 0.0;
        double somma2 = 0.0;



        for (int i = 0; i<xdata.length; i++){

            somma+= Math.abs(xdata[i]);
        }

        for (int i = 0; i<xdata.length; i++){

            somma2+= xdata[i]*xdata[i];
        }

        //Rivedere teorema dell'equivalenza

        somma2 = Math.sqrt(somma2);

        System.out.println("Norma vettoriale 1 =" +somma);
        System.out.println("Norma vettoriale 2 =" +somma2);

        if (somma2<=somma*c1){

            if (somma2*c2>=somma){

                System.out.println("Il Teorema dell'equivalenza è valido");

            }
        }

    }
}

