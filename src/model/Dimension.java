package model;

import java.util.ArrayList;

public class Dimension {
    private String name;
    private int coef;
    private ArrayList<Metric> metrics;

    public Dimension(String name, int coef){
        this.name = name;
        this.coef = coef;
        this.metrics = new ArrayList<>();
    }

    public void addMetric(Metric m) {metrics.add(m); }

    public double Calc(){
        double lelx = 0;
        int totalcoef = 0;
        for (Metric m : metrics){
            lelx += m.Calc() * m.getCoef();
            totalcoef += m.getCoef();
        }
        return totalcoef == 0 ? 0 : lelx / totalcoef;
    }

    public String getName() {return name;}
    public int getCoef() {return coef;}
    public ArrayList<Metric> getMetrics() {return metrics;
    }
}
