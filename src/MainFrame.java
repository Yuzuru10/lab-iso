import gui.*;
import model.Scenario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private static final String[] CARDS = {"step1", "step2", "step3", "step4", "step5"};
    private int currentStep = 0;

    private CardLayout cardLayout = new CardLayout();
    private JPanel    cardPanel   = new JPanel(cardLayout);

    private StepIndicator indicator = new StepIndicator();

    private Step1Panel step1 = new Step1Panel();
    private Step2Panel step2 = new Step2Panel();
    private Step3Panel step3 = new Step3Panel();
    private Step4Panel step4 = new Step4Panel();
    private Step5Panel step5 = new Step5Panel();

    private JButton btnBack = new JButton("bacK");
    private JButton btnNext = new JButton("Next");

    public MainFrame() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 620);
        setMinimumSize(new Dimension(700, 550));
        setLocationRelativeTo(null);

        cardPanel.add(step1, CARDS[0]);
        cardPanel.add(step2, CARDS[1]);
        cardPanel.add(step3, CARDS[2]);
        cardPanel.add(step4, CARDS[3]);
        cardPanel.add(step5, CARDS[4]);

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        navBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 220)));
        navBar.add(btnBack);
        navBar.add(btnNext);

        getContentPane().add(indicator, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(navBar,    BorderLayout.SOUTH);

        btnBack.addActionListener(this::onBack);
        btnNext.addActionListener(this::onNext);

        refreshNav();
    }

    private void onNext(ActionEvent e) {
        if (currentStep == 0 && !step1.validate(this)) return;
        if (currentStep == 1 && !step2.validate(this)) return;

        if (currentStep == 1) {
            Scenario chosen = step2.getSelectedScenario();
            step3.loadScenario(chosen);
            step4.loadScenario(chosen);
            step5.loadScenario(chosen);
        }

        currentStep++;
        cardLayout.show(cardPanel, CARDS[currentStep]);
        indicator.setStep(currentStep);
        refreshNav();
    }

    private void onBack(ActionEvent e) {
        currentStep--;
        cardLayout.show(cardPanel, CARDS[currentStep]);
        indicator.setStep(currentStep);
        refreshNav();
    }

    private void refreshNav() {
        btnBack.setEnabled(currentStep > 0);
        btnNext.setEnabled(currentStep < CARDS.length - 1);
        btnNext.setText(currentStep == CARDS.length - 2 ? "Analyse" : "Next");
    }
}
