import com.panayotis.gnuplot.JavaPlot;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by fabio on 10/05/2016.
 */

public class logActivity {

    public static void main(String[] args) {
        JavaPlot p = new JavaPlot("C:/Program Files/gnuplot/bin/gnuplot.exe");
        p.addPlot("[2.5:3.7] log(x)");
        p.plot();
    }

}
