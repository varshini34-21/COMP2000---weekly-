import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private Grid grid = new Grid(20, 20, 35, 10);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point mousePos = getMousePosition();
        grid.paint(g, mousePos);
        repaint(); // refresh continuously for hover effect
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Week 3 - Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.add(new Main());
        frame.setVisible(true);
    }
}
