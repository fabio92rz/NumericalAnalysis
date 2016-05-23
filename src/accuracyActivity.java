import java.util.Scanner;

/**
 * Created by fabio on 09/05/2016.
 */
public class accuracyActivity {

    public static void main(String args[]){

        int a;
        float b;

        int contA = 0;
        int contB = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci un numero intero: ");
        a = input.nextInt();

         System.out.println("Inserisci un numero in doppia precisione");
        b = input.nextFloat();

        do {
            a = a/2;
            contA++;
        } while (a>0);

        do {
            b = b/2;
            contB++;
        } while (b>0);

        System.out.println("Precisione di macchina per un intero è: " + contA);
        System.out.println("Precisone di macchina per un numero in doppia precisione è:" + contB);

    }

}
