import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
    protected Cell loc;                
    protected Color color;              
    protected List<Polygon> display;    

    
    public Actor(Cell inLoc, Color color, ArrayList<Polygon> display) {
        this.loc = inLoc;
        this.color = color;
        this.display = display;
    }

   
    public void paint(Graphics g) {
        if (display != null) {
            for (Polygon p : display) {
                g.setColor(color);
                g.fillPolygon(p);       
                g.setColor(Color.BLACK);
                g.drawPolygon(p);       
            }
        }
    }

   
    public abstract void updateShape();

   
    public void pickUpItems(Cell cell) {
        List<Collectible> picked = cell.collectItems();
        for (Collectible item : picked) {
            pickUp(item);
        }
    }

   
    public void pickUp(Collectible item) {
        System.out.println(getClass().getSimpleName() + " picked up " + item.getClass().getSimpleName());
    }
}
