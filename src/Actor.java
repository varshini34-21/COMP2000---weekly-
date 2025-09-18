import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
    protected Color color;
    public Cell loc;
    protected List<Polygon> display;
    public Inventory<Collectible> inventory = new Inventory<>();

    public Actor(Cell loc, Color color, List<Polygon> display) {
        this.loc = loc;
        this.color = color;
        this.display = display;
    }

    public void paint(Graphics g) {
        for (Polygon p : display) {
            Polygon shifted = new Polygon();
            for (int i = 0; i < p.npoints; i++) {
                shifted.addPoint(p.xpoints[i] + loc.x, p.ypoints[i] + loc.y);
            }
            g.setColor(color);
            g.fillPolygon(shifted);
            g.setColor(Color.BLACK);
            g.drawPolygon(shifted);
        }
    }

    public void pickUpItems(Cell cell) {
        List<Collectible> items = cell.collectItems();
        for (Collectible i : items) {
            inventory.addItem(i);
            System.out.println(this.getClass().getSimpleName() + " picked up " + i.getName());
            i.use();
        }
    }
}
