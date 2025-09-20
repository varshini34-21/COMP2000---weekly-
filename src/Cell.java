import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Cell extends Rectangle {
    public static int size = 35;  
    char col;
    int row;

    private List<Collectible> items = new ArrayList<>();

    public Cell(char inCol, int inRow, int x, int y) {
        super(x, y, size, size);
        this.col = inCol;
        this.row = inRow;
    }

    public void paint(Graphics g, Point mousePos) {
        if (contains(mousePos)) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);

       
        for (Collectible item : getItems()) {
            if (item instanceof Drawable) {
                ((Drawable) item).paint(g, x, y);
            }
        }
    }

    @Override
    public boolean contains(Point p) {
        if (p != null) {
            return super.contains(p);
        } else {
            return false;
        }
    }

    public int leftOfComparison(Cell c) {
        return Integer.compare(col, c.col);
    }

    public int aboveComparison(Cell c) {
        return Integer.compare(row, c.row);
    }

    public void addItem(Collectible item) {
        items.add(item);
    }

    public List<Collectible> collectItems() {
        List<Collectible> copy = new ArrayList<>(items);
        items.clear();
        return copy;
    }

    public List<Collectible> getItems() {
        return items;
    }

   
    public void pickUpBy(Actor actor) {
        List<Collectible> pickedUp = collectItems();
        for (Collectible item : pickedUp) {
            actor.pickUp(item);  
        }
    }
}
