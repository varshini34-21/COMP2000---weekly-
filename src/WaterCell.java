import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class WaterCell extends Cell implements EnterRule {
    public WaterCell(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canEnter(Actor actor) {
        return actor instanceof Bird; 
    }

    @Override
    public void paint(Graphics g, Point offset) {
        g.setColor(Color.CYAN);
        g.fillRect(x + offset.x, y + offset.y, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x + offset.x, y + offset.y, SIZE, SIZE);
        for (Collectible item : items) {
            if (item instanceof Drawable) {
                ((Drawable) item).paint(g, x + offset.x, y + offset.y);
            }
        }
    }
}
