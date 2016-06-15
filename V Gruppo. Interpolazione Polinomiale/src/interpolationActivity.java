/**
 * Created by fabio on 23/05/2016.
 */

import Jama.Matrix;
import org.apache.commons.math3.*;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class interpolationActivity {


    public static void NewtonConstruction(int n, double x[], double y[], double a[]) {
        double w, p;
        a[0] = y[0];

        for (int k = 1; k <= n; k++) {
            w = 1;
            p = 0;

            for (int j = 0; j < k; j++) {
                p = p + (a[j] * w);
                w = w * (x[k] - x[j]);
            }
            a[k] = (y[k] - p) / w;
        }
    }

    public static double NewtonEvaluation(int n, double xx, double x[], double a[]) {
        double px, xd;
        px = a[n];

        for (int k = n - 1; k >= 0; k--) {
            xd = xx - x[k];
            px = a[k] + px * xd;
        }
        return px;
    }

    public static void arrayInterval(double[] v, double n, double a, double b){

        double c = (b - a)/(n - 1.0);
        v[0] = a;

        for (int i = 1; i<v.length; i++){

            v[i] = v[i - 1] + c;
        }
    }

    public static void arrayFormula(double[] v, double n, double a, double b){

        for (int i = 0; i<v.length; i++){

            v[i] = ((b-a)*Math.cos(((2.0*(n-1.0-i)+1.0)*(3.14159))/(2.0*(n)))+(b+a))/2.0;
        }

        for (int j = 0; j<v.length; j++){

            System.out.print(v[j]);
        }
    }


    public static void main(String args[]) {

        double n;
        double[] v = new double[25];
        double[] p = new double[25];

        double a = 2;
        double b = 3;



        Scanner input = new Scanner(System.in);

        System.out.println("Inserire grado del polinomio, compreso tra 5 e 25");
        n = input.nextDouble();

        arrayInterval(v, n, a, b);
        arrayFormula(p, n, a, b);


    }
}
