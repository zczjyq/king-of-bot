package 实验5;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class ColoredStar extends JFrame {

    public ColoredStar() {
        setTitle("Colored Star");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        GeneralPath star = new GeneralPath();

        int[] xPoints = {55, 67, 109, 73, 83, 55, 27, 37, 1, 43};
        int[] yPoints = {0, 36, 36, 54, 96, 72, 96, 54, 36, 36};

        star.moveTo(xPoints[0], yPoints[0]);

        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i], yPoints[i]);
        }

        star.closePath();

        GradientPaint gradient = new GradientPaint(0, 0, Color.RED, 200, 200, Color.BLUE);
        g2d.setPaint(gradient);
        g2d.fill(star);
    }

    public static void main(String[] args) {
        new ColoredStar();
    }
}
