import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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

/**
 * Created by fabio on 26/06/2016.
 */
public class Main extends JFrame {
    final int size = 25;

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
        renderer.setSeriesPaint(3, Color.BLUE);
        renderer.setSeriesPaint(4, Color.WHITE);
        renderer.setSeriesPaint(5, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));
        renderer.setSeriesStroke(4, new BasicStroke(2.0f));
        renderer.setSeriesStroke(5, new BasicStroke(2.0f));
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

    private static class F1 extends Function {

    	F1(double from, double to) {
            super(from, to);
        }

        @Override
        double getY(double x) {
            if (!range.contains(x))
                throw new RuntimeException("Invalid x");
            return x / (1+ 25*x*x);
        }
    }

    private double getXiForSmallInterval(int i, int n) {
        return Math.cos(Math.PI * ((2d*(n-i)+1d) / (2d*(n+1d))));
    }

    private double getXiForLargeInterval(Range range, int i, int n) {
        return 0.5*(range.to - range.from) * getXiForSmallInterval(i, n) + 0.5d*(range.from + range.to);
    }

    private java.util.List<Point2D.Double> createDataSeries(Function function, int n){
        java.util.List<Point2D.Double> series = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = getXi(function.getRange(), i, n);
            series.add(new Point2D.Double(x, function.getY(x)));
        }
        return series;
    }
    private java.util.List<Point2D.Double> createPoints(Function function, int n){
        java.util.List<Point2D.Double> series = new ArrayList<>();
        double x0 = function.getRange().from;
        double xto = function.getRange().to;
        for (int i = 1 ; i < n ; i++) {
            final double x = x0 + (xto - x0) / n * i;
            series.add(new Point2D.Double(x,function.getY(x)));
        }
        return series;
    }

    private Double getXi(Range range, int i, int n) {
        if (new Range(-1, 1).contains(range))
            return getXiForSmallInterval(i, n);
        return getXiForLargeInterval(range, i, n);
    }

    private XYSeries toGraph(java.util.List<Point2D.Double> points, String title){
        XYSeries series = new XYSeries(title);
        for (Point2D.Double p : points) {
            series.add(p.x, p.y);
        }
        return series;
    }

    private XYDataset createDataset(int n) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(newtonify(createDataSeries(new F2(-10, 10), n), "F2 [-10,10] Newton"));
        dataset.addSeries(newtonify(createPoints(new F1(-1, 1), size), createDataSeries(new F1(-1, 1), n), "F1 [-1,1] Newton"));
//        dataset.addSeries(newtonify(createPoints(new F2(-1, 1), size), createDataSeries(new F2(-1, 1), n), "F2 [-1,1] Newton"));
        dataset.addSeries(newtonify(createPoints(new F3(2, 3), size), createDataSeries(new F3(2, 3), n), "F3 Newton"));
//        dataset.addSeries(toGraph(createPoints(new F2(-10, 10), size), "F2 [-10,10]"));
        dataset.addSeries(toGraph(createPoints(new F1(-1, 1), size), "F1 [-1,1]"));
//        dataset.addSeries(toGraph(createPoints(new F2(-1, 1), size), "F2 [-1,1]"));
        dataset.addSeries(toGraph(createPoints(new F3(2, 3), size), "F3"));
        return dataset;
    }

//    private static class Point {
//        double x, y;
//        Point(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//    }

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

	double recursiveDifference(java.util.List<Point2D.Double> points, int from, int to)
		{
		if (from == to)
			return points.get(from).y;
		if (from == to - 1)
			return (points.get(to).y - points.get(from).y) / (points.get(to).x - points.get(from).x);
		return (recursiveDifference(points, from + 1, to) - recursiveDifference(points, from, to - 1))
				/ (points.get(to).x - points.get(from).x);
		}

	private XYSeries newtonify(java.util.List<Point2D.Double> points, java.util.List<Point2D.Double> source, String title)
		{
		XYSeries newSeries = new XYSeries(title);
		List<Double> recs = new ArrayList<>();
		for (int i = 0; i < source.size(); i++)
			recs.add(recursiveDifference(source, 0, i));
		for (Point2D.Double p : points)
			{
			double y = 0;
			double x = p.x;;
			for (int i = 0; i < source.size(); i++)
				{
				double rec = recs.get(i);
				for (int j = 0; j < i; j++)
					rec *= x - source.get(j).x;
				y += rec;
				}
			newSeries.add(x, y);
			}
		return newSeries;
		}

    public static void main(String[] args) {
        Main chart = new Main("Newton approximation", "Newton approximation", 25);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}
