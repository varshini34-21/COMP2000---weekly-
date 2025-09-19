import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GrassCell extends Cell implements EnterRule {
    public GrassCell(char inCol, int inRow, int x, int y) {
        super(inCol, inRow, x, y);
    }

    @Override
    public boolean canEnter(Actor actor) {
        return true;
    }

    @Override
    public void paint(Graphics g, Point mousePos) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, Cell.size, Cell.size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, Cell.size, Cell.size);

        for (Collectible item : getItems()) {
            if (item instanceof Drawable) {
                ((Drawable) item).paint(g, x, y);
            }
        }
    }
}
