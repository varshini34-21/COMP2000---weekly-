import java.awt.Color;
import java.awt.Graphics;
public class Seed implements Collectible, Drawable {
  @Override
  public String getName() {
      return "Seed";
  }

  @Override
  public void use() {
      System.out.println("The bird pecks at the seed!");
      System.out.println("PECK PECK PECK");
  }
  public void paint(Graphics g, int x, int y) {
    g.setColor(Color.BLACK);
    g.fillOval(x + 15, y + 15, 8, 8);
}
}
