import java.awt.*;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by lamba on 15/06/2016.
 */
public class LambaPro {
    private ArrayList<Point> punti;
    private ArrayList<Double> differenze_divise;

    public LambaPro(ArrayList<Point> punti){
        this.punti=punti;
        differenze_divise = new ArrayList<Double>();

        ArrayList<Point> peti = new ArrayList<Point>();

        differenze_divise.add(0, punti.get(0).getY());
        for(int i = 1; i < punti.size() - 1; i++){
            peti.clear();
            for(int j = 0; j <= i; j++){
                peti.add(j, punti.get(j));
            }
            differenze_divise.add(i, elabora_differenze(peti));
        }
    }

    private double elabora_differenze(ArrayList<Point> points){
        if(points.size()==2) return (points.get(2).getY() - points.get(1).getY())/(points.get(2).getX() - points.get(1).getX());
        else {
            ArrayList<Point> a = new ArrayList<Point>(), b = new ArrayList<Point>();
            for(int i = 0; i < points.size()-1; i++) {
                a.add(i, points.get(i + 1));
                b.add(i, points.get(i));
            }
            return (elabora_differenze(a) - elabora_differenze(b))/(points.get(points.size()-1).getX() - points.get(1).getX());
        }
    }

    public String GetNewtonPolynomial(){

    }
}