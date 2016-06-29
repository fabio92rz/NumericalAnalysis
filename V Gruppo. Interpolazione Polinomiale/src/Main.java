import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by fabio on 26/06/2016.
 */
public class Main extends JFrame {
    final int size = 49;

    public Main(String applicationTitle, String chartTitle, int n) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "x",
                "y",
                createDataset(n),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1120, 734));
        final XYPlot plot = xylineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static class Range {
        double from, to;
        Range(double from, double to) {
            this.from = from;
            this.to = to;
        }

        public boolean contains(double x) {
            return x >= from && x <= to;
        }

        public boolean contains(Range range) {
            return contains(range.from) && contains(range.to);
        }

    }
    private static abstract class Function {
        Range range;
        Function(double from, double to) {
            range = new Range(from, to);
        }
        abstract double getY(double x);

        public Range getRange() {
            return range;
        }
    }

    private static class F3 extends Function {

        F3(double from, double to) {
            super(from, to);
        }

        @Override
        double getY(double x) {
            if (!range.contains(x))
                throw new RuntimeException("Invalid x");
            return Math.log(x);
        }
    }

    private static class F2 extends Function {

        F2(double from, double to) {
            super(from, to);
        }

        @Override
        double getY(double x) {
            if (!range.contains(x))
                throw new RuntimeException("Invalid x");
            return x / (1+ x*x);
        }
    }

    private double getXiForSmallInterval(int i, int n) {
        return Math.cos(Math.PI * (2*(n-i)+1) / (2*(n+1)));
    }

    private double getXiForLargeInterval(Range range, int i, int n) {
        return 0.5*(range.to - range.from) * getXiForSmallInterval(i, n) + 0.5d*(range.from + range.to);
    }

    private java.util.List<Point> createDataSeries(Function function, int n){
        java.util.List<Point> series = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = getXi(function.getRange(), i, n);
            series.add(new Point(x, function.getY(x)));
        }
        return series;
    }
    private java.util.List<Point> createPoints(Function function, int n){
        java.util.List<Point> series = new ArrayList<>();
        double x0 = function.getRange().from;
        double xto = function.getRange().to;
        for (int i = 1 ; i < size ; i++) {
            final double x = x0 + (xto - x0) / size * i;
            series.add(new Point(x,function.getY(x)));
        }
        return series;
    }

    private Double getXi(Range range, int i, int n) {
        if (new Range(-1, 1).contains(range))
            return getXiForSmallInterval(i, n);
        return getXiForLargeInterval(range, i, n);
    }

    private XYSeries toGraph(java.util.List<Point> points, String title){
        XYSeries series = new XYSeries(title);
        for (Point p : points) {
            series.add(p.x, p.y);
        }
        return series;
    }

    private XYDataset createDataset(int n) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(newtonify(createDataSeries(new F2(-10, 10), n), "F2 [-10,10] Newton"));
        dataset.addSeries(newtonify(createDataSeries(new F2(-1, 1), n), "F2 [-1,1] Newton"));
        dataset.addSeries(newtonify(createDataSeries(new F3(2, 3), n), "F3 Newton"));
//        dataset.addSeries(toGraph(createPoints(new F2(-10, 10), size), "F2 [-10,10]"));
        dataset.addSeries(toGraph(createPoints(new F2(-1, 1), size), "F2 [-1,1]"));
        dataset.addSeries(toGraph(createPoints(new F3(2, 3), size), "F3"));
        return dataset;
    }

    private static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
    p := y[0];
    f := 1;
    for i=0 to n do
        a[i] := y[i];
        for j=n to i+1 do
            a[j] := (a[j]-a[j-1])/(x[j]-x[j-i-1]);
            od;
        f := f*(x-x[i-1]);
        p := p + a[i]*f;
        od;
     */

    double recursiveDifference(double x, java.util.List<Point> points) {
        double result = points.get(0).y;
        double f = 1;
        double[] as = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            as[i] = points.get(i).y;
            for (int j = points.size() -1; j >= i+1; j--){
                as[j] = (as[j] - as[j-1]) / (points.get(j).x - points.get(j-i-1).x);
            }
            f *= (x - points.get(i).x);
            result += as[i] * f;
        }
        return result;
    }

    private XYSeries newtonify(java.util.List<Point> points, String title) {
        java.util.List<Double> xs = new ArrayList<>();
        for (Point p : points) {
            xs.add(p.x);
        }
        XYSeries newSeries = new XYSeries(title);
        for (int i = 1 ; i < size ; i++) {
            final double x = xs.get(0) + (xs.get(xs.size()-1) - xs.get(0)) / size * i;
            newSeries.add(x,recursiveDifference(x, points));
        }
        return newSeries;
    }

    public static void main(String[] args) {
        Main chart = new Main("Newton approximation", "Newton approximation", 16);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}
