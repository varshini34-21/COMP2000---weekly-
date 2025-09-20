import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon; 
public class Fish implements Collectible, Drawable {
  @Override
  public String getName() {
      return "Fish";
  }

  @Override
  public void use() {
      System.out.println("The cat is eating the fish!");
      System.out.println("MEOW");
  }
  public void paint(Graphics g, int x, int y) {
    g.setColor(Color.PINK);
    Polygon body = new Polygon();
        body.addPoint(x + 5,  y + 15);
        body.addPoint(x + 15, y + 10);
        body.addPoint(x + 25, y + 10);
        body.addPoint(x + 35, y + 15);
        body.addPoint(x + 25, y + 20);
        body.addPoint(x + 15, y + 20);
        g.fillPolygon(body);
        Polygon tail = new Polygon();
        tail.addPoint(x + 35, y + 15);
        tail.addPoint(x + 45, y + 10);
        tail.addPoint(x + 45, y + 20);
        g.fillPolygon(tail);

        g.setColor(Color.BLACK);
        g.drawPolygon(body);
        g.drawPolygon(tail);

        g.setColor(Color.WHITE);
        g.fillOval(x + 8, y + 13, 4, 4);
        g.setColor(Color.BLACK);
        g.fillOval(x + 9, y + 14, 2, 2);
}
}
