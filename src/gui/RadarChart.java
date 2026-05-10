package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

public class RadarChart extends JPanel {
    private List<String> labels;
    private List<Double> values;

    public RadarChart(List<String> labels, List<Double> values) {
        this.labels = labels;
        this.values = values;
        setPreferredSize(new java.awt.Dimension(340, 300));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth()  / 2;
        int cy = getHeight() / 2;
        int R  = Math.min(cx, cy) - 50;
        int n = labels.size();
        if (n < 3) return;

        double step = 2 * Math.PI / n;
        double startAngle = -Math.PI / 2;

        g2.setColor(new Color(200, 200, 200));
        for (int ring = 1; ring <= 5; ring++) {
            int r = R * ring / 5;
            Path2D poly = new Path2D.Double();
            for (int i = 0; i < n; i++) {
                double angle = startAngle + i * step;
                double x = cx + r * Math.cos(angle);
                double y = cy + r * Math.sin(angle);
                if (i == 0) poly.moveTo(x, y); else poly.lineTo(x, y);
            }
            poly.closePath();
            g2.draw(poly);
        }

        g2.setColor(new Color(180, 180, 180));
        for (int i = 0; i < n; i++) {
            double angle = startAngle + i * step;
            int x2 = (int)(cx + R * Math.cos(angle));
            int y2 = (int)(cy + R * Math.sin(angle));
            g2.drawLine(cx, cy, x2, y2);
        }

        Path2D data = new Path2D.Double();
        for (int i = 0; i < n; i++) {
            double angle = startAngle + i * step;
            double ratio = (values.get(i) - 1.0) / 4.0;
            double x = cx + R * ratio * Math.cos(angle);
            double y = cy + R * ratio * Math.sin(angle);
            if (i == 0) data.moveTo(x, y); else data.lineTo(x, y);
        }
        data.closePath();
        g2.setColor(new Color(0, 102, 204, 80));
        g2.fill(data);
        g2.setColor(new Color(0, 102, 204));
        g2.setStroke(new BasicStroke(2f));
        g2.draw(data);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
        FontMetrics fm = g2.getFontMetrics();
        for (int i = 0; i < n; i++) {
            double angle = startAngle + i * step;
            int lx = (int)(cx + (R + 20) * Math.cos(angle));
            int ly = (int)(cy + (R + 20) * Math.sin(angle));
            String lbl = labels.get(i);
            int sw = fm.stringWidth(lbl);
            int tx = lx - sw / 2;
            int ty = ly + fm.getAscent() / 2;
            g2.drawString(lbl, tx, ty);
        }
    }
}