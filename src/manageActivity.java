import java.util.Scanner;

/**
 * Created by fabio on 09/05/2016.
 */
public class manageActivity {

    public static Scanner input = new Scanner(System.in);
    private final int[] values;
    private int size;

    public manageActivity(int... values) {
        this.values = values.clone();
        this.size = values.length;
    }

    int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (values[i] == value) {
                return i + 1;
            }
        }
        return -1;
    }

    boolean remove(int value) {
        for (int i = 0; i < size; i++) {
            if (values[i] == value) {
                --size;
                for (; i < size; ++i) {
                    values[i] = values[i + 1];
                }
                return true;
            }
        }
        return false;
    }

    private static int[] insertElement(int original[], int element, int index) {
        int length = original.length;
        int destination[] = new int[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }

    void print(int[] c) {
        System.out.print("Elementi nell'array: ");
        for (int i = 0; i < size; ++i) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {

        System.out.print("Quante cifre si intende inserire? ");
        int nelems = input.nextInt();
        int c[] = new int[nelems];

        System.out.println("Inserire le cifre");
        for (int i = 0; i < c.length; i++)
            c[i] = input.nextInt();

        manageActivity arr = new manageActivity(c);
        arr.print(c);

        int b;
        int h;

        System.out.println("Inserisci un numero da aggiungere: ");
        h = input.nextInt();

        System.out.println("Inserisci un numero da eliminare: ");
        b = input.nextInt();

        int index = arr.indexOf(c.length);
        if (index < 0) {

            System.out.println("Elemento non trovato");

        } else {

            System.out.println("Elemento trovato nella posizione " + index);
        }

        insertElement(c, h, index);

        if (!arr.remove(b)) {
            System.out.println("Elemento non trovato");
        }
        arr.print(c);

    }
}