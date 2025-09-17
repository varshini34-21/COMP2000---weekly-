import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Cell extends Rectangle {
  static int size = 35;
  char col;
  int row;

  private List<Item> items = new ArrayList<>();

  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public List<Item> collectItems() {
    List<Item> collected = new ArrayList<>(items);
    items.clear();
    return collected;
  }

  public List<Item> getItems() {
    return items;
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

    if (!items.isEmpty()) {
      g.setColor(Color.ORANGE);
      g.fillOval(x + size/3, y + size/3, size/3, size/3);
    }
  }

  public boolean contains(Point p) {
    if (p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }
}
