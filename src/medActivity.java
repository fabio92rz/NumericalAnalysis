import java.util.Scanner;

/**
 * Created by fabio on 05/05/2016.
 */
public class medActivity {

    public static void main(String args[]){

        int sum = 0, inputNum;
        int counter;
        float mean;
        Scanner NumScanner = new Scanner(System.in);
        Scanner charScanner = new Scanner(System.in);

        System.out.println("Digita il numero di elementi di cui vuoi calcolare la media.");

        counter = NumScanner.nextInt();

        System.out.println("Per favore inserisci " + counter + " numeri:");

        for(int x = 1; x<=counter ;x++){
            inputNum = NumScanner.nextInt();
            sum = sum + inputNum;
            System.out.println();
        }

        mean = sum / counter;
        System.out.println("La media dei " + counter + " numeri inseriti è " + mean);

    }
}
