import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public class Cell extends Rectangle {
    public static final int size = 20; 

   
    public Cell(int x, int y) {
        super(x, y, size, size);
    }

   
    public Cell(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        if (p == null) return false;  
        return super.contains(p);
    }

    
    public void paint(Graphics g, Point mousePos) {
        if (mousePos != null && this.contains(mousePos)) {
            g.setColor(Color.LIGHT_GRAY); 
        } else {
            g.setColor(Color.WHITE);     
        }
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);        
        g.drawRect(x, y, width, height);
    }
}
