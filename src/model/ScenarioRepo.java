package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioRepo {
    private HashMap<String, ArrayList<Scenario>> data = new HashMap<>();

    public ScenarioRepo() {
        data.put("Health", buildHealthScenarios());
        data.put("Education", buildEducationScenarios());
    }

    public ArrayList<Scenario> getScenariosForMode(String mode) {
        return data.getOrDefault(mode, new ArrayList<>());
    }

    private ArrayList<Scenario> buildEducationScenarios() {
        ArrayList<Scenario> list = new ArrayList<>();
        list.add(buildEducationScenarioC());
        list.add(buildEducationScenarioD());
        return list;
    }

    private Scenario buildEducationScenarioC() {
        Scenario s = new Scenario("Scenario C-Team Alpha", "Education");

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("SUS Score",       50, 1,  0, 100, "points", 89));
        usability.addMetric(new Metric("Onboarding Time", 50, -1, 0,  60, "min",     5));
        s.addDimesJuice2gnd(usability);

        Dimension perf = new Dimension("Performance Efficiency", 20);
        perf.addMetric(new Metric("Video Start Time",  50, -1,  0,  15, "sec",   2));
        perf.addMetric(new Metric("Concurrent Exams",  50,  1,  0, 600, "users", 480));
        s.addDimesJuice2gnd(perf);

        Dimension acc = new Dimension("Accessibility", 20);
        acc.addMetric(new Metric("WCAG Compliance",     50, 1, 0, 100, "%", 78));
        acc.addMetric(new Metric("Screen Reader Score", 50, 1, 0, 100, "%", 65));
        s.addDimesJuice2gnd(acc);

        Dimension rel = new Dimension("Reliability", 20);
        rel.addMetric(new Metric("Uptime", 50,  1, 95, 100, "%",   99));
        rel.addMetric(new Metric("MTTR",   50, -1,  0, 120, "min", 15));
        s.addDimesJuice2gnd(rel);

        Dimension func = new Dimension("Functional Suitability", 15);
        func.addMetric(new Metric("Feature Completion",   50, 1, 0, 100, "%", 92));
        func.addMetric(new Metric("Assignment Submit Rate",50, 1, 0, 100, "%", 85));
        s.addDimesJuice2gnd(func);

        return s;
    }

    private Scenario buildEducationScenarioD() {
        Scenario s = new Scenario("Scenario D-Team Beta", "Education");

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("SUS Score",       50, 1,  0, 100, "points", 62));
        usability.addMetric(new Metric("Onboarding Time", 50, -1, 0,  60, "min",    30));
        s.addDimesJuice2gnd(usability);

        Dimension perf = new Dimension("Performance Efficiency", 20);
        perf.addMetric(new Metric("Video Start Time",  50, -1,  0,  15, "sec",   8));
        perf.addMetric(new Metric("Concurrent Exams",  50,  1,  0, 600, "users", 200));
        s.addDimesJuice2gnd(perf);

        Dimension acc = new Dimension("Accessibility", 20);
        acc.addMetric(new Metric("WCAG Compliance",     50, 1, 0, 100, "%", 45));
        acc.addMetric(new Metric("Screen Reader Score", 50, 1, 0, 100, "%", 30));
        s.addDimesJuice2gnd(acc);

        Dimension rel = new Dimension("Reliability", 20);
        rel.addMetric(new Metric("Uptime", 50,  1, 95, 100, "%",   97));
        rel.addMetric(new Metric("MTTR",   50, -1,  0, 120, "min", 55));
        s.addDimesJuice2gnd(rel);

        Dimension func = new Dimension("Functional Suitability", 15);
        func.addMetric(new Metric("Feature Completion",    50, 1, 0, 100, "%", 70));
        func.addMetric(new Metric("Assignment Submit Rate", 50, 1, 0, 100, "%", 60));
        s.addDimesJuice2gnd(func);

        return s;
    }

    private ArrayList<Scenario> buildHealthScenarios() {
        ArrayList<Scenario> list = new ArrayList<>();
        list.add(buildHealthScenarioA());
        list.add(buildHealthScenarioB());
        return list;
    }

    private Scenario buildHealthScenarioA() {
        Scenario s = new Scenario("Scenario A-Hospital Alpha", "Health");

        Dimension usability = new Dimension("Usability", 30);
        usability.addMetric(new Metric("Task Success Rate", 50,  1, 0, 100, "%",  88));
        usability.addMetric(new Metric("Error Rate",        50, -1, 0,  20, "%",   3));
        s.addDimesJuice2gnd(usability);

        Dimension security = new Dimension("Security", 30);
        security.addMetric(new Metric("Auth Failures",      50, -1, 0, 100, "count", 5));
        security.addMetric(new Metric("Encryption Coverage",50,  1, 0, 100, "%",    95));
        s.addDimesJuice2gnd(security);

        Dimension reliability = new Dimension("Reliability", 25);
        reliability.addMetric(new Metric("System Uptime", 50,  1, 95, 100, "%",   99.5));
        reliability.addMetric(new Metric("Recovery Time", 50, -1,  0,  60, "min",    5));
        s.addDimesJuice2gnd(reliability);

        Dimension perf = new Dimension("Performance", 15);
        perf.addMetric(new Metric("Response Time",    50, -1, 0, 5000, "ms",  400));
        perf.addMetric(new Metric("Throughput",       50,  1, 0, 1000, "req/s", 750));
        s.addDimesJuice2gnd(perf);

        return s;
    }

    private Scenario buildHealthScenarioB() {
        Scenario s = new Scenario("Scenario B-Clinic Beta", "Health");

        Dimension usability = new Dimension("Usability", 30);
        usability.addMetric(new Metric("Task Success Rate", 50,  1, 0, 100, "%",  60));
        usability.addMetric(new Metric("Error Rate",        50, -1, 0,  20, "%",  10));
        s.addDimesJuice2gnd(usability);

        Dimension security = new Dimension("Security", 30);
        security.addMetric(new Metric("Auth Failures",       50, -1, 0, 100, "count", 22));
        security.addMetric(new Metric("Encryption Coverage", 50,  1, 0, 100, "%",     70));
        s.addDimesJuice2gnd(security);

        Dimension reliability = new Dimension("Reliability", 25);
        reliability.addMetric(new Metric("System Uptime", 50,  1, 95, 100, "%",   96));
        reliability.addMetric(new Metric("Recovery Time", 50, -1,  0,  60, "min", 30));
        s.addDimesJuice2gnd(reliability);

        Dimension perf = new Dimension("Performance", 15);
        perf.addMetric(new Metric("Response Time", 50, -1, 0, 5000, "ms",   1200));
        perf.addMetric(new Metric("Throughput",    50,  1, 0, 1000, "req/s", 300));
        s.addDimesJuice2gnd(perf);

        return s;
    }
}
