import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class Stage extends JPanel {
    private List<Actor> actors = new ArrayList<>();
    private Cell[][] grid;
    private int gridSize = 5; 

    public Stage() {
       
        grid = new Cell[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new Cell(i * 40, j * 40); 
            }
        }

        
        Dog dog = new Dog(grid[1][1]);
        Cat cat = new Cat(grid[2][2]);
        Bird bird = new Bird(grid[3][3]);

        actors.add(dog);
        actors.add(cat);
        actors.add(bird);

        
        grid[1][1].addItem(new Bone());
        grid[2][2].addItem(new Fish());
        grid[3][3].addItem(new Seed());

        
        dog.pickUpItems(grid[1][1]);
        cat.pickUpItems(grid[2][2]);
        bird.pickUpItems(grid[3][3]);

        
        dog.showInventory();
        cat.showInventory();
        bird.showInventory();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

       
        int cellSize = 40;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }

       
        for (Actor a : actors) {
            a.paint(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment 1 Stage Demo");
        Stage stage = new Stage();
        frame.add(stage);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
