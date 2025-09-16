import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Main extends JPanel {
    private Stage stage;

    public Main() {
      Grid grid = new Grid();
      stage = new Stage(grid);

      stage.addActor(new Cat(grid.cellAtColRow(2, 2)));
      stage.addActor(new Dog(grid.cellAtColRow(5, 7)));
      stage.addActor(new Bird(grid.cellAtColRow(10, 4)));
  }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        stage.paint(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Week 4 Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new Main());
        frame.setVisible(true);
    }
}
