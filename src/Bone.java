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

   
    g.fillRect(x + 10, y + 14, 20, 8);

    
    g.fillOval(x + 5, y + 10, 8, 8);
    g.fillOval(x + 5, y + 18, 8, 8);

    
    g.fillOval(x + 25, y + 10, 8, 8);
    g.fillOval(x + 25, y + 18, 8, 8);

    
    g.setColor(Color.BLACK);
    g.drawRect(x + 10, y + 14, 20, 8);
    g.drawOval(x + 5, y + 10, 8, 8);
    g.drawOval(x + 5, y + 18, 8, 8);
    g.drawOval(x + 25, y + 10, 8, 8);
    g.drawOval(x + 25, y + 18, 8, 8);
} 
}

