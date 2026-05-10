package gui;

import model.Scenario;
import model.ScenarioRepo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Step2Panel extends JPanel {

    // 2a quality type
    private ButtonGroup typeGroup   = new ButtonGroup();
    private JRadioButton rbProduct  = new JRadioButton("Product Quality");
    private JRadioButton rbProcess  = new JRadioButton("Process Quality");

    // 2b mode
    private ButtonGroup modeGroup   = new ButtonGroup();
    private JRadioButton rbHealth   = new JRadioButton("Health");
    private JRadioButton rbEducation= new JRadioButton("Education");

    // 2c scenario
    private ButtonGroup scenGroup   = new ButtonGroup();
    private JPanel scenarioPanel    = new JPanel();
    private ArrayList<JRadioButton> scenarioButtons = new ArrayList<>();

    private ScenarioRepo repo = new ScenarioRepo();
    private Scenario selectedScenario = null;

    public Step2Panel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(makeTitle("Step 2 -Define Quality Dimensions"));
        add(Box.createVerticalStrut(10));

        add(makeSectionLabel("2a quality Type"));
        typeGroup.add(rbProduct);
        typeGroup.add(rbProcess);
        rbProduct.setSelected(true);
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(rbProduct); typePanel.add(rbProcess);
        add(typePanel);
        add(Box.createVerticalStrut(10));

        add(makeSectionLabel("2b Mode"));
        modeGroup.add(rbHealth);
        modeGroup.add(rbEducation);
        rbHealth.setSelected(true);
        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        modePanel.add(rbHealth); modePanel.add(rbEducation);
        add(modePanel);

        ActionListener modeListener = e -> loadScenarios();
        rbHealth.addActionListener(modeListener);
        rbEducation.addActionListener(modeListener);
        add(Box.createVerticalStrut(10));

        add(makeSectionLabel("2c Scenario"));
        scenarioPanel.setLayout(new BoxLayout(scenarioPanel, BoxLayout.Y_AXIS));
        scenarioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(scenarioPanel);

        loadScenarios(); // initial load
    }

    private void loadScenarios() {
        scenarioPanel.removeAll();
        scenarioButtons.clear();
        scenGroup = new ButtonGroup();
        selectedScenario = null;

        String mode = rbHealth.isSelected() ? "Health" : "Education";
        ArrayList<Scenario> scenarios = repo.getScenariosForMode(mode);

        for (Scenario sc : scenarios) {
            JRadioButton rb = new JRadioButton(sc.getname());
            scenGroup.add(rb);
            scenarioButtons.add(rb);
            rb.addActionListener(e -> selectedScenario = sc);
            scenarioPanel.add(rb);
        }

        if (!scenarioButtons.isEmpty()) {
            scenarioButtons.get(0).setSelected(true);
            selectedScenario = scenarios.get(0);
        }

        scenarioPanel.revalidate();
        scenarioPanel.repaint();
    }

    public boolean validate(JFrame parent) {
        if (selectedScenario == null) {
            JOptionPane.showMessageDialog(parent,
                "select scenario", "yoh", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public Scenario getSelectedScenario() { return selectedScenario; }
    public String getQualityType() { return rbProduct.isSelected() ? "Product" : "Process"; }
    public String getMode()        { return rbHealth.isSelected()  ? "Health"  : "Education"; }

    private JLabel makeTitle(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 20));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel makeSectionLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 14));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }
}