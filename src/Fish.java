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
    g.setColor(Color.MAGENTA);
    Polygon drop = new Polygon();
    drop.addPoint(x + 17, y + 10);
    drop.addPoint(x + 20, y + 18);
    drop.addPoint(x + 14, y + 18);
    g.fillPolygon(drop);

    g.setColor(Color.BLACK);
    g.drawPolygon(drop);
}
}
