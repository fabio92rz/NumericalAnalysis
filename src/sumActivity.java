import javax.swing.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by fabio on 04/05/2016.
 */
public class sumActivity {

    public static void main(String args[]){


        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        int i,n;
        double s,x;

        System.out.println("quanti numeri vuoi sommare? ");
        n=in.nextInt();
        System.out.println("si calcola la somma di n= " + n + " numeri reali ");
        s=0;
        i=0;

        while(i<n){
            i=i+1;
            System.out.print("introdurre il valore numero " + i + "    ");
            x= in.nextDouble();
            System.out.println("x= " +x);
            s=s+x;
        }
        System.out.println("somma = " + s);
    }
}
