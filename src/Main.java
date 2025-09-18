import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main window = new Main();
        window.run();
    }

    class Canvas extends JPanel {
        Stage stage = new Stage();

        public Canvas() {
            setPreferredSize(new Dimension(1024, 720));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);      // clear background
            stage.paint(g);      // let Stage draw itself
        }
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            repaint();
        }
    }
}
