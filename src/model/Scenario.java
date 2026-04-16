package model;
import java.util.ArrayList;
public class Scenario {
    private String name;
    private String mode;
    private ArrayList<Dimension> dimesJuice;

    public Scenario(String name, String mode){
        this.name = name;
        this.mode = mode;
        this.dimesJuice = new ArrayList<>();
    }
    public void addDimesJuice2gnd(Dimension d) {
        dimesJuice.add(d);
    }
    public String getname() {return name;}
    public String getMode() {return mode;}
    public ArrayList<Dimension> getDimesJuice() {return dimesJuice;}
}
