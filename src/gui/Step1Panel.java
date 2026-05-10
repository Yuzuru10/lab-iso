package gui;

import javax.swing.*;
import java.awt.*;

public class Step1Panel extends JPanel {

    private JTextField tfUsername = new JTextField(20);
    private JTextField tfSchool   = new JTextField(20);
    private JTextField tfSession  = new JTextField(20);

    public Step1Panel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel title = new JLabel("Step 1 Profile", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        addRow(form, gbc, 0, "Username:", tfUsername);
        addRow(form, gbc, 1, "Sko00l:",   tfSchool);
        addRow(form, gbc, 2, "Session Neym:", tfSession);

        add(form, BorderLayout.CENTER);
    }

    private void addRow(JPanel p, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridx = 0; gbc.gridy = row;
        p.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        p.add(field, gbc);
    }

    public boolean validate(JFrame parent) {
        if (tfUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(parent,
                "enter ur username!1!1!!", "yoq", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfSchool.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(parent,
                "enter Skool name!!11!1!", "yoq", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tfSession.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(parent,
                "Entr session naaame!!!11!", "yoq", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public String getUsername() { return tfUsername.getText().trim(); }
    public String getSchool()   { return tfSchool.getText().trim(); }
    public String getSession()  { return tfSession.getText().trim(); }
}
