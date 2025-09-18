import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    public int x, y;
    public static final int SIZE = 40;
    protected List<Collectible> items = new ArrayList<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addItem(Collectible item) {
        items.add(item);
    }

    public List<Collectible> collectItems() {
        List<Collectible> temp = new ArrayList<>(items);
        items.clear();
        return temp;
    }

    public void paint(Graphics g, Point offset) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + offset.x, y + offset.y, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x + offset.x, y + offset.y, SIZE, SIZE);

        for (Collectible item : items) {
            if (item instanceof Drawable) {
                ((Drawable) item).paint(g, x + offset.x, y + offset.y);
            }
        }
    }

    public boolean contains(Point p) {
        return p.x >= x && p.x < x + SIZE &&
               p.y >= y && p.y < y + SIZE;
    }
}
