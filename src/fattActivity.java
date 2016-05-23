import java.util.Scanner;

/**
 * Created by fabio on 05/05/2016.
 */
public class fattActivity {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int a;
        float b;
        double c;

        System.out.println("Inserisci un numero intero per il fattoriale: ");
        a = input.nextInt();

        System.out.println("Inserisci un numero reale per il fattoriale: ");
        b = input.nextFloat();

        System.out.println("Inserisci un numero in doppia precisione per il fattoriale");
        c = input.nextDouble();


        System.out.println("Il fattoriale del numero intero " + a + " vale " +fatt(a));

        System.out.println("Il fattoriale del numero reale " + b + " vale "+fatt2(b));

        System.out.println("Il fattoriale del numero in doppia precisione " + c+ " vale" +fattFloat(c));
    }

    static int fatt(int x) {
        int i;
        int f=1;

        for(i=1; i<=x; i=i+1) {
            f=f*i;
        }

        return f;
    }

    static int fatt2(float x) {
        int i;
        int f=1;

        for(i=1; i<=x; i=i+1) {
            f=f*i;
        }

        return f;
    }

    static int fattFloat(double x) {
        int i;
        int f=1;

        for(i=1; i<=x; i=i+1) {
            f=f*i;
        }
        return f;
    }
}
