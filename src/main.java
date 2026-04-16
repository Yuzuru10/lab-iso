import model.Dimension;
import model.Metric;
import model.Scenario;

public class main {
    public static void main(String[] args){
        Metric m1 = new Metric("helele", 2, 1,0,100,"nig/ga", 70);

        Dimension d1 = new Dimension("fisfis", 3);
        d1.addMetric(m1);

        Scenario s1 = new Scenario("bilili", "abow");
        s1.addDimesJuice2gnd(d1);

        System.out.println("Sinaryo: " + s1.getname());

        for(Dimension d : s1.getDimesJuice()){
            System.out.println("meyvesuyu: " + d.getName());

            for(Metric m : d.getMetrics()){
                System.out.println("metrik: " + m.getName() + " uiy" + m.Calc());
            }
        }
        System.out.println("Cappy Pulpy: " + d1.Calc());
    }
}
