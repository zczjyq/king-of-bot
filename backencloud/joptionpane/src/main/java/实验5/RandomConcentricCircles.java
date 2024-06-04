package 实验5;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomConcentricCircles extends JFrame {
    public RandomConcentricCircles() {
        setTitle("Random Concentric Circles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Random random = new Random();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 20;
        int gap = 10;

        for (int i = 0; i < 80; i++) {
            Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g.setColor(randomColor);
            g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
            radius += gap;
        }
    }
}
