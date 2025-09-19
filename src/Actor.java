import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
    protected Color color;
    public Cell loc;
    public Inventory<Collectible> inventory = new Inventory<>();
    protected List<Polygon> display;

    public Actor(Cell loc, Color color, List<Polygon> display) {
        this.loc = loc;
        this.color = color;
        this.display = display;
    }

    public void paint(Graphics g) {
        for (Polygon p : display) {
            g.setColor(color);
            g.fillPolygon(p);
            g.setColor(Color.BLACK);
            g.drawPolygon(p);
        }
    }

    public void pickUpItems(Cell cell) {
        for (Collectible i : cell.collectItems()) {
            inventory.addItem(i);
            System.out.println(this.getClass().getSimpleName() + " picked up " + i.getName());
            i.use();
        }
    }
}
