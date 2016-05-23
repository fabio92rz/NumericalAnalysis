import javax.swing.*;

/**
 * Created by fabio on 04/05/2016.
 */
public class equationActivity {

    public static void main(String args[]) {

        float a, b, c, delta;
        String tmp;
        tmp = JOptionPane.showInputDialog("Inserisci a");
        a = Integer.valueOf(tmp);
        tmp = JOptionPane.showInputDialog("Inserisci b");
        b = Integer.valueOf(tmp);
        tmp = JOptionPane.showInputDialog("Inserisci c");
        c = Integer.valueOf(tmp);

        if (a == 0) {

            System.out.println("l'equazione non è di secondo grado");

        } else {

            System.out.println("Calcolo il discriminante");

            delta = (b * b) - 4 * a * c;

            if (delta < 0) {

                System.out.println("Le soluzioni sono complesse e coniugate");

            } else {

                if (delta == 0) {

                    double x12 = -b / (2 * a);

                    System.out.println("2 soluzioni reali e concidenti:" + x12);

                } else {

                    System.out.println("2 soluzioni reali e distinte");
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    System.out.println("x1:" + x1);
                    System.out.println("x2:" + x2);
                }

            }


        }


    }
}

