import java.awt.Color;
import java.awt.Graphics;
public class Bone implements Collectible, Drawable {
  @Override
  public String getName() {
      return "Bone";
  }

  @Override
  public void use() {
      System.out.println("The dog GOT the bone!");
      System.out.println("BARK BARK");
  }
  public void paint(Graphics g, int x, int y) {
    g.setColor(Color.WHITE);
    // middle rectangle
    g.fillRect(x + 12, y + 14, 10, 6);
    // circles at ends
    g.fillOval(x + 8, y + 12, 6, 10);
    g.fillOval(x + 20, y + 12, 6, 10);

    g.setColor(Color.BLACK);
    g.drawRect(x + 12, y + 14, 10, 6);
    g.drawOval(x + 8, y + 12, 6, 10);
    g.drawOval(x + 20, y + 12, 6, 10);
} 
}

