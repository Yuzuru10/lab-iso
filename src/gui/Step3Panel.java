package gui;

import model.Dimension;
import model.Metric;
import model.Scenario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Step3Panel extends JPanel {

    private JPanel contentPanel = new JPanel();

    public Step3Panel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("Step 3 - Plan Measurement", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(contentPanel);
        add(scroll, BorderLayout.CENTER);
    }

    public void loadScenario(Scenario scenario) {
        contentPanel.removeAll();

        for (Dimension dim : scenario.getDimesJuice()) {
            JLabel dimLabel = new JLabel(dim.getName() + "  (Coefficient: " + dim.getCoef() + ")");
            dimLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            dimLabel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));
            dimLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(dimLabel);

            String[] cols = {"Metric", "Coefficient", "Direction", "Range", "Unit"};
            DefaultTableModel model = new DefaultTableModel(cols, 0) {
                public boolean isCellEditable(int r, int c) { return false; }
            };

            for (Metric m : dim.getMetrics()) {
                String dir = m.Getdih() == 1 ? "Higher ↑" : "Lower ↓";
                String range = (int)m.getMin() + " - " + (int)m.getMax();
                model.addRow(new Object[]{m.getName(), m.getCoef(), dir, range, m.getunit()});
            }

            JTable table = new JTable(model);
            table.setRowHeight(24);
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
            JScrollPane tableScroll = new JScrollPane(table);
            int tableHeight = (dim.getMetrics().size() + 1) * 25 + 5;
            tableScroll.setPreferredSize(new java.awt.Dimension(700, tableHeight));
            tableScroll.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, tableHeight));
            tableScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(tableScroll);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}