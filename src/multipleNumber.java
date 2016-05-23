import java.awt.*;
import java.util.Scanner;

/**
 * Created by fabio on 04/05/2016.
 */
public class multipleNumber {


    public static void main (String args[]){


        Scanner input = new Scanner(System.in);

        System.out.print("Scrivi il primo numero intero: ");
        int n1 = input.nextInt();

        System.out.print("Scrivi il secondo numero intero: ");
        int n2 = input.nextInt();

        System.out.print("Scrivi un numero intero: ");
        int n3 = input.nextInt();

        if (n1 % n2 == 0){

            System.out.print("Il numero " + n1 + " è multiplo di " + n2);

        }else {

            System.out.print("Il numero " + n1 + " non è multiplo di " + n2);
        }

        if (n3 % 2 != 0){

            System.out.print("\nIl numero " + n3 + " è dispari");

        }else {

            System.out.print("\nil numero " + n3 + " 4" + " è pari");
        }
    }
}
