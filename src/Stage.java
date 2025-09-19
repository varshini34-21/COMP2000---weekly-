import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JPanel;

public class Stage extends JPanel implements MouseListener {
    private Grid grid;
    private List<Actor> actors = new ArrayList<>();
    private Actor selectedActor = null;
    private List<Cell> highlightedCells = new ArrayList<>();
    private Point mousePos = new Point(0, 0);

    public Stage() {
        grid = new Grid(); 
        this.addMouseListener(this);

        
        Dog dog = new Dog(grid.cellAtColRow('B', 2).get());
        Cat cat = new Cat(grid.cellAtColRow('F', 5).get());
        Bird bird = new Bird(grid.cellAtColRow('J', 10).get());

        actors.add(dog);
        actors.add(cat);
        actors.add(bird);

       
        grid.cellAtColRow('C', 3).get().addItem(new Bone());
        grid.cellAtColRow('G', 7).get().addItem(new Fish());
        grid.cellAtColRow('L', 12).get().addItem(new Seed());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

      
        grid.paint(g, mousePos);

     
        grid.paintOverlay(g, highlightedCells, new Color(0, 0, 255, 100));

        for (Actor a : actors) {
            a.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        Optional<Cell> clickedOpt = grid.cellAtPoint(p);

        if (clickedOpt.isEmpty()) return;
        Cell clicked = clickedOpt.get();

        if (selectedActor == null) {
            
            for (Actor a : actors) {
                if (a.loc == clicked) {
                    selectedActor = a;
                    highlightedCells = grid.getRadius(clicked, 2); 
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

    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
