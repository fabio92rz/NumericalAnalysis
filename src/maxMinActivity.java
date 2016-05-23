import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.util.Scanner;

/**
 * Created by fabio on 05/05/2016.
 */
public class maxMinActivity {

    public static Scanner input = new Scanner(System.in);

    public static void main(String args[]) {

        System.out.print("Quante cifre si intende inserire? ");
        int nelems = input.nextInt();
        int c[] = new int [nelems];

        System.out.println("Inserire le cifre");
        for (int i = 0; i < c.length; i++)
            c[i] = input.nextInt();

        maxMin(c);


    }

    public static void maxMin(int[] v) {

        int min, max;
        min = v[0];
        max = v[0];

        for (int i = 0; i < v.length; i++) {

            if (v[i] < min)

                min = v[i];

            else if (v[i] > max)
                max = v[i];
        }

        System.out.println("Minimo = " + min);
        System.out.println("Massimo = " + max);
    }

}
