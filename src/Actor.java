import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
    protected Color color;
    protected Cell loc;
    protected List<Polygon> display;

    // Inventory can hold any Collectible (Bone, Fish, Seed, etc.)
    protected Inventory<Collectible> inventory = new Inventory<>();

    public Actor(Cell loc, Color color, List<Polygon> display) {
        this.loc = loc;
        this.color = color;
        this.display = display;
    }

    // Draw the actor’s polygons
    public void paint(Graphics g) {
        for (Polygon p : display) {
            g.setColor(color);
            g.fillPolygon(p);
            g.setColor(Color.GRAY);
            g.drawPolygon(p);
        }
    }

    // Pick up all items from the given cell
    public void pickUpItems(Cell cell) {
        List<Collectible> items = cell.collectItems();
        for (Collectible i : items) {
            inventory.addItem(i);
            System.out.println(this.getClass().getSimpleName() + " picked up " + i.getName());
        }
    }

    // Show what’s inside the inventory
    public void showInventory() {
        inventory.showItems();
    }

    // Use all items in inventory
    public void useInventory() {
        if (!inventory.isEmpty()) {
            inventory.useAll();
        } else {
            System.out.println(this.getClass().getSimpleName() + " has nothing to use.");
        }
    }
}
