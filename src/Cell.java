import java.awt.*;

public class Cell {
    private int x, y, size;

    public Cell(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void paint(Graphics g, Point mousePos) {
        // Highlight if mouse is inside this cell
        if (mousePos != null &&
            mousePos.x >= x && mousePos.x <= x + size &&
            mousePos.y >= y && mousePos.y <= y + size) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, size, size);
            g.setColor(Color.BLACK);
        }
        g.drawRect(x, y, size, size);
    }
}
