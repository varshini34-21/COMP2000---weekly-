import java.awt.Color;
import java.awt.Graphics;
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
        g.setColor(Color.ORANGE);
        g.fillOval(x + 10, y + 10, 12, 8); // oval body
        g.setColor(Color.BLACK);
        g.drawOval(x + 10, y + 10, 12, 8);
    }
}
