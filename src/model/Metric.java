package model;

public class Metric {
    private String name;
    private int coef;
    private int direction; // 1 is higher is better ,f is lower
    private double min,max;
    private String unit;
    private double value;

    public Metric(String name,int coef,int direction,double min, double max , String unit, double value){
        this.name = name;
        this.coef = coef;
        this.direction = direction;
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = value;
    }

    public double Calc(){
        double x;
        if(direction == 1){
            x = 1 + (value - min) / (max - min) * 4;
        }
        else{
            x = 5 - (value - min) / (max - min) * 4;
        }
        x = Math.max(1.0, Math.min(5.0, x));
        return Math.round(x *2) /2.0;
    }
    public String getName() {return name;}
    public int getCoef() {return coef;}
    public int Getdih() {return direction;}
    public double getMin() {return min;}
    public double getMax() {return max;}
    public String getunit() {return unit;}
    public double getVal() {return value;}
}
