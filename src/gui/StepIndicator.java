package gui;

import javax.swing.*;
import java.awt.*;

public class StepIndicator extends JPanel {

    private static final String[] STEP_NAMES = {"Profile", "Define", "Plan", "Collect", "Analyse"};
    private int currentStep = 0;
    private JLabel[] labels = new JLabel[STEP_NAMES.length];

    public StepIndicator() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 210, 220)));
        for (int i = 0; i < STEP_NAMES.length; i++) {
            if (i > 0) {
                JLabel arrow = new JLabel("→");
                arrow.setForeground(Color.GRAY);
                add(arrow);
            }
            labels[i] = new JLabel((i + 1) + ". " + STEP_NAMES[i]);
            labels[i].setFont(new Font("SansSerif", Font.PLAIN, 13));
            add(labels[i]);
        }
        refresh();
    }
    public void setStep(int step) {
        currentStep = step;
        refresh();
    }
    private void refresh() {
        for (int i = 0; i < labels.length; i++) {
            if (i < currentStep) {
                // completed
                labels[i].setText("✓ " + STEP_NAMES[i]);
                labels[i].setForeground(new Color(40, 167, 69));
                labels[i].setFont(new Font("SansSerif", Font.PLAIN, 13));
            } else if (i == currentStep) {
                // active
                labels[i].setText((i + 1) + ". " + STEP_NAMES[i]);
                labels[i].setForeground(new Color(0, 102, 204));
                labels[i].setFont(new Font("SansSerif", Font.BOLD, 13));
            } else {
                // future
                labels[i].setText((i + 1) + ". " + STEP_NAMES[i]);
                labels[i].setForeground(Color.GRAY);
                labels[i].setFont(new Font("SansSerif", Font.PLAIN, 13));
            }
        }
    }
}