import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
  protected Color color;
  protected Cell loc;
  protected List<Polygon> display;

  protected Inventory<Item> inventory = new Inventory<>();

  
  public Actor(Cell loc, Color color, List<Polygon> display) {
    this.loc = loc;
    this.color = color;
    this.display = display;
  }

  public void paint(Graphics g) {
    for (Polygon p : display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.GRAY);
      g.drawPolygon(p);
    }
  }

  public void pickUpItems(Cell cell) {
    for (Item i : cell.collectItems()) {
      inventory.addItem(i);
      System.out.println(this.getClass().getSimpleName() + " picked up " + i.getName());
    }
  }

  public void useInventory() {
    if (!inventory.isEmpty()) {
      inventory.useAll();
    }
  }
}
