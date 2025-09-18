import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Stage extends JPanel implements MouseListener {
    private Grid grid;
    private List<Actor> actors = new ArrayList<>();
    private Actor selectedActor = null;
    private List<Cell> highlightedCells = new ArrayList<>();

    public Stage() {
        grid = new Grid(10, 10);
        this.addMouseListener(this);

        
        Dog dog = new Dog(grid.getCellAt(1, 1));
        Cat cat = new Cat(grid.getCellAt(2, 2));
        Bird bird = new Bird(grid.getCellAt(3, 3));

        actors.add(dog);
        actors.add(cat);
        actors.add(bird);

        
        grid.getCellAt(4, 4).addItem(new Bone());
        grid.getCellAt(5, 5).addItem(new Fish());
        grid.getCellAt(6, 6).addItem(new Seed());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

       
        grid.paint(g);

        
        g.setColor(new Color(0, 0, 255, 100));
        for (Cell c : highlightedCells) {
            g.fillRect(c.x, c.y, Cell.SIZE, Cell.SIZE);
        }

      
        for (Actor a : actors) {
            a.paint(g);
        }

       
        int infoX = 450;
        int y = 30;
        g.setColor(Color.BLACK);
        g.drawString("=== GAME INFO ===", infoX, y);
        y += 20;

        for (Actor a : actors) {
            g.drawString(a.getClass().getSimpleName(), infoX, y);
            y += 15;
            g.drawString("Location: (" + a.loc.x/Cell.SIZE + "," + a.loc.y/Cell.SIZE + ")", infoX+10, y);
            y += 15;
            g.drawString("Inventory:", infoX+10, y);
            y += 15;

            if (a.inventory.isEmpty()) {
                g.drawString("- Empty", infoX+20, y);
                y += 15;
            } else {
                for (Collectible item : a.inventory.getItems()) {
                    g.drawString("- " + item.getName(), infoX+20, y);
                    y += 15;
                }
            }
            y += 20;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        Cell clicked = grid.getCellAt(p);

        if (clicked == null) return;

        if (selectedActor == null) {
            
            for (Actor a : actors) {
                if (clicked.contains(new Point(a.loc.x, a.loc.y))) {
                    selectedActor = a;
                    highlightedCells = getMoveOptions(clicked);
                    repaint();
                    return;
                }
            }
        } else {
            
            if (highlightedCells.contains(clicked)) {
                selectedActor.loc = clicked;
                selectedActor.pickUpItems(clicked);
            }
            selectedActor = null;
            highlightedCells.clear();
            repaint();
        }
    }

    private List<Cell> getMoveOptions(Cell from) {
        List<Cell> moves = new ArrayList<>();
        int[][] dirs = { {1,0}, {-1,0}, {0,1}, {0,-1} };
        int fromRow = from.x / Cell.SIZE;
        int fromCol = from.y / Cell.SIZE;

        for (int[] d : dirs) {
            Cell c = grid.getCellAt(fromRow + d[0], fromCol + d[1]);
            if (c != null) moves.add(c);
        }
        return moves;
    }

    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
