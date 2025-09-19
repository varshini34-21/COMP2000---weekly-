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
    g.fillRect(x + 10, y + 10, 10, 5); 
    g.setColor(Color.BLACK);
    g.drawRect(x + 10, y + 10, 10, 5);
}
  }

