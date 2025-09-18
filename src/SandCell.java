import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SandCell extends Cell {
    public SandCell(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g, Point offset) {
        g.setColor(Color.YELLOW);
        g.fillRect(x + offset.x, y + offset.y, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x + offset.x, y + offset.y, SIZE, SIZE);
    }
}
