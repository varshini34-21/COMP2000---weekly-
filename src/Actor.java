import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
    protected Cell location;
    protected List<Polygon> shapes;

    public Actor(Cell location, List<Polygon> shapes) {
        this.location = location;
        this.shapes = shapes;
    }

    public void paint(Graphics g) {
        for (Polygon p : shapes) {
            g.drawPolygon(p);
            g.fillPolygon(p);
        }
    }
}
