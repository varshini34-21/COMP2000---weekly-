import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame {
    private Stage stage;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main window = new Main();
            window.setVisible(true);
        });
    }

    // Canvas panel that draws the Stage and handles mouse clicks
    class Canvas extends JPanel implements MouseListener {
        public Canvas(Stage stage) {
            this.stage = stage;
            setPreferredSize(new Dimension(800, 800));
            addMouseListener(this);
        }

        private Stage stage;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Point mouseLoc = getMousePosition();
            stage.paint(g, mouseLoc);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            stage.mouseClicked(e.getX(), e.getY());
            repaint();
        }

        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Assignment 1 Grid Game");

        // Create Stage
        stage = new Stage();

        // Wrap it in a Canvas JPanel
        Canvas canvas = new Canvas(stage);
        this.setContentPane(canvas);

        this.pack();
        this.setLocationRelativeTo(null); // center on screen

        // Repaint loop (20 FPS)
        Timer timer = new Timer(50, _ -> repaint());
        timer.start();
    }
}
