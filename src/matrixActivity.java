import java.util.Scanner;

/**
 * Created by fabio on 09/05/2016.
 */
public class matrixActivity {

    public static Scanner input = new Scanner(System.in);

    public static void leggiMatrice( int m[][] ) {
        System.out.println("Inserire gli elementi della matrice - " + m.length + " righe, " );

        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i].length + " elementi per la riga " + i);
            for ( int j = 0; j < m[i].length; j++ )
                m[i][j] = input.nextInt();
            System.out.println();
        }
    }

    public static void scriviMatrice( int m[][] ) {
        for (int i = 0; i < m.length; i++) {
            for ( int j = 0; j < m[i].length; j++ )
                System.out.print( m[i][j] + " ");
            System.out.println();
        }
    }


    public static void matricePerVettore( int m[][], int v[], int ris[] ) {
        for (int i = 0; i < m.length; i++){
            int s = 0;
            for ( int j = 0; j < v.length; j++ )
                s = s + m[i][j] * v[j];
            ris[i] = s;
        }
    }

    public static void sommaMatrici( int m1[][], int m2[][], int ris[][] ) {
        for (int i = 0; i < m1.length; i++)
            for ( int j = 0; j < m1[i].length; j++ )
                ris[i][j] = m1[i][j] + m2[i][j];
    }

    public static void sostituisciRiga( int m[][], int v[], int r ) {
        for (int i = 0; i < v.length; i++)
            m[r][i] = v[i];
    }

    public static void sostituisciColonna( int m[][], int v[], int c ) {
        for (int i = 0; i < v.length; i++)
            m[i][c] = v[i];
    }

    public static boolean triangolareSup( int m[][]) {
        for (int i = 1; i < m.length; i++)
            for ( int j = 0; j < i; j++ )
                if (m[i][j] != 0)
                    return false;
        return true;
    }

    public static void matriceTrasposta (int m[][], int t[][]) {
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                t[i][j] = m[j][i];
    }

    public static boolean almenoUnPari (int m[][]) {
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                if (m[i][j] % 2 == 0)
                    return true;
        return false;
    }

    public static boolean tuttiPari (int m[][]) {
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                if (m[i][j] % 2 != 0)
                    return false;
        return true;
    }

    public static void stampaCorniceEsterna (int m[][]) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++)
                if ( i == 0 || i == m.length-1 || j == 0 || j == m[0].length-1 )
                    System.out.print(m[i][j] + " ");
                else
                    System.out.print("  ");
            System.out.println();
        }
    }

    public static int sommaCorniceEsterna (int m[][]) {
        int somma = 0;
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                if ( i == 0 || i == m.length-1 || j == 0 || j == m[0].length-1 )
                    somma += m[i][j];
        return somma;
    }

    public static boolean sommaCrocePari (int m[][]) {
        int somma = 0;
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                if ( i == j || j == m.length - 1 - i )
                    somma += m[i][j];
        return (somma % 2 == 0);
    }

    public static boolean arrayUguali(int a[], int b[]) {
        if (a.length == b.length) {
            for (int i = 0; i < a.length && i < b.length; i++)
                if (a[i] != b[i])
                    return false;
            return true;
        }
        return false;
    }

    public static boolean arrayInMatriceRiga( int m[][], int v[]) {
        for (int i = 0; i < m.length; i++)
            if (arrayUguali(m[i], v))
                return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Inserire le dimensioni della matrice");
        System.out.print("Numero di righe   : ");
        int nr = input.nextInt();
        System.out.print("Numero di colonne : ");
        int nc = input.nextInt();

        int m[][] = new int [nr][nc];
        int ris[][] = new int [nr][nc];

        leggiMatrice(m);
        scriviMatrice(m);

        System.out.println("Inserire il vettore da moltiplicare per la matrice");
        int v[] = new int[nc];
        int r[] = new int[nr];
        leggiArray(v);
        matricePerVettore(m,v,r);
        System.out.println("Il vettore risultante è : ");
        stampaArray(r);

        System.out.println("Inserire una seconda matrice da sommare alla prima");
        int m1[][] = new int [nr][nc];
        leggiMatrice(m1);
        sommaMatrici(m,m1,ris);
        System.out.println("La matrice risultante è : ");
        scriviMatrice(ris);

        System.out.println("Quale riga vuoi sostituire?");
        int s = input.nextInt();
        System.out.println("Inserire il vettore che sostituisce la riga " + s);
        int v1[] = new int[nc];
        leggiArray(v1);
        sostituisciRiga(m,v1,s);
        System.out.println("La matrice risultante è : ");
        scriviMatrice(m);

        System.out.println("Quale colonna vuoi sostituire?");
        s = input.nextInt();
        System.out.println("Inserire il vettore che sostituisce la colonna " + s);
        int v2[] = new int[nr];
        leggiArray(v2);
        sostituisciColonna(m,v2,s);
        System.out.println("La matrice risultante � : ");
        scriviMatrice(m);

        if (triangolareSup(m))
            System.out.println("La matrice è triangolare superiore.");
        else
            System.out.println("La matrice non è triangolare superiore.");

        System.out.println("La matrice trasposta della matrice originaria è la seguente...");
        int ris2 [][] = new int [nc][nr];
        matriceTrasposta(m,ris2);
        scriviMatrice(ris2);

        if (almenoUnPari(m))
            System.out.println("La matrice contiene almeno un elemento pari.");
        else
            System.out.println("La matrice non contiene nessun elemento pari.");

        if (tuttiPari(m))
            System.out.println("Tutti gli elementi della matrice sono pari.");
        else
            System.out.println("La matrice contiene almeno un elemento non pari.");

        System.out.println("La cornice più esterna della matrice originaria è la seguente...");
        stampaCorniceEsterna(m);

        System.out.println("La somma degli elementi della cornice più esterna della matrice originaria è : "
                + sommaCorniceEsterna(m));

        System.out.println("Inserire un array per verificare se è uguale ad almeno una riga nella matrice originaria.");
        int v3[] = new int[nc];
        leggiArray(v3);
        if (arrayInMatriceRiga(m, v3))
            System.out.println("La matrice ha almeno una riga uguale al vettore.");
        else
            System.out.println("La matrice non ha nessuna riga uguale all'array.");

    }

    public static void leggiArray(int a[]){
        System.out.println("Inserire " + a.length + " numeri interi:");
        for (int i=0; i < a.length; i++)
            a[i] = input.nextInt();
    }

    public static void stampaArray(int a[]){
        for (int i=0; i < a.length; i++)
            System.out.println(a[i]);
    }
}