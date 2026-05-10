package gui;

import model.Dimension;
import model.Scenario;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Step5Panel extends JPanel {
    private JPanel contentPanel = new JPanel();

    public Step5Panel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Step 5 - Analyse", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(contentPanel);
        add(scroll, BorderLayout.CENTER);
    }

    public void loadScenario(Scenario scenario) {
        contentPanel.removeAll();

        List<String>  dimNames = new ArrayList<>();
        List<Double>  dimScores = new ArrayList<>();
        Dimension     worstDim  = null;
        double        worstScore = Double.MAX_VALUE;

        JLabel secA = new JLabel("5a Dimension Scores");
        secA.setFont(new Font("SansSerif", Font.BOLD, 15));
        secA.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        secA.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(secA);

        for (Dimension dim : scenario.getDimesJuice()) {
            double score = dim.Calc();
            dimNames.add(dim.getName());
            dimScores.add(score);

            if (score < worstScore) {
                worstScore = score;
                worstDim   = dim;
            }

            JPanel row = new JPanel(new BorderLayout(10, 0));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            row.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));

            JLabel lbl = new JLabel(String.format("%-28s %.1f / 5.0", dim.getName(), score));
            lbl.setFont(new Font("Monospaced", Font.PLAIN, 13));
            lbl.setPreferredSize(new java.awt.Dimension(250, 24));

            JProgressBar bar = new JProgressBar(0, 100);
            bar.setValue((int)((score / 5.0) * 100));
            bar.setStringPainted(false);
            bar.setForeground(scoreColor(score));
            bar.setPreferredSize(new java.awt.Dimension(300, 20));

            row.add(lbl, BorderLayout.WEST);
            row.add(bar, BorderLayout.CENTER);
            row.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
            contentPanel.add(row);
        }

        contentPanel.add(Box.createVerticalStrut(20));

        JLabel secB = new JLabel("5b Radar Chart");
        secB.setFont(new Font("SansSerif", Font.BOLD, 15));
        secB.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        secB.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(secB);

        RadarChart radar = new RadarChart(dimNames, dimScores);
        radar.setAlignmentX(Component.LEFT_ALIGNMENT);
        radar.setMaximumSize(new java.awt.Dimension(350, 310));
        contentPanel.add(radar);

        contentPanel.add(Box.createVerticalStrut(20));

        JLabel secC = new JLabel("5c Gap Analysis");
        secC.setFont(new Font("SansSerif", Font.BOLD, 15));
        secC.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        secC.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(secC);

        if (worstDim != null) {
            double gap   = 5.0 - worstScore;
            String level = qualityLevel(worstScore);
            JPanel gapPanel = new JPanel(new GridLayout(4, 1, 4, 4));
            gapPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            gapPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 120));
            gapPanel.setBorder(BorderFactory.createTitledBorder("Weakest Dimension"));
            gapPanel.add(new JLabel("Dimension: " + worstDim.getName() + "  |  Score: " + String.format("%.2f", worstScore)));
            gapPanel.add(new JLabel("Gap: " + String.format("%.2f", gap)));
            JLabel lvlLabel = new JLabel("Quality Level: " + level);
            lvlLabel.setForeground(scoreColor(worstScore));
            lvlLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            gapPanel.add(lvlLabel);
            gapPanel.add(new JLabel("This dimension has the lowest score and requires the most improvement."));
            contentPanel.add(gapPanel);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private Color scoreColor(double score) {
        if (score >= 4.0) return new Color(40, 167, 69);
        if (score >= 2.5) return new Color(255, 165, 0);
        return new Color(220, 53, 69);
    }
    private String qualityLevel(double score) {
        if (score >= 4.5) return "Excellent";
        if (score >= 3.5) return "Good";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor";
    }
}






