import javax.swing.*;
import java.util.Scanner;

/**
 * Created by fabio on 04/05/2016.
 */
public class prodActivity {

    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        int r;

        System.out.print("Inserisci un numero reale R: ");
        r=scanner.nextInt();

        fattIt(r);

    }

    public static int fattIt(int n) {

        int f=1;
        int i=0;

        while (i!=n) {
            i++;
            f *= i;
        }

        System.out.println("la produttoria è " +f);
        return f;
    }
}
