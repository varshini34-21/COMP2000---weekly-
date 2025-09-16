import java.awt.Color;
import java.awt.Graphics;

public abstract class Actor {
    protected Cell location;
    protected Color color;

    public Actor(Cell location, Color color) {
        this.location = location;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(location.x, location.y, location.width, location.height);
    }
}
