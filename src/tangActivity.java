import com.panayotis.gnuplot.JavaPlot;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by fabio on 10/05/2016.
 */
public class tangActivity {

    public static void main(String[] args) {
        JavaPlot p = new JavaPlot("C:/Program Files/gnuplot/bin/gnuplot.exe");
        p.addPlot("[0.1:1.5] tan(x)/x");
        p.plot();
    }
}
