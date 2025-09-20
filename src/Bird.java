import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Bird extends Actor {
    public Bird(Cell inLoc) {
        super(inLoc, Color.YELLOW, createShape(inLoc));
    }

    private static ArrayList<Polygon> createShape(Cell loc) {
        ArrayList<Polygon> display = new ArrayList<>();

        Polygon wing1 = new Polygon();
        wing1.addPoint(loc.x + 5, loc.y + 5);
        wing1.addPoint(loc.x + 15, loc.y + 17);
        wing1.addPoint(loc.x + 5, loc.y + 17);
        display.add(wing1);

        Polygon wing2 = new Polygon();
        wing2.addPoint(loc.x + 30, loc.y + 5);
        wing2.addPoint(loc.x + 20, loc.y + 17);
        wing2.addPoint(loc.x + 30, loc.y + 17);
        display.add(wing2);

        Polygon body = new Polygon();
        body.addPoint(loc.x + 15, loc.y + 10);
        body.addPoint(loc.x + 20, loc.y + 10);
        body.addPoint(loc.x + 20, loc.y + 25);
        body.addPoint(loc.x + 15, loc.y + 25);
        display.add(body);

        return display;
    }

    @Override
    public void updateShape() {
        this.display = createShape(loc);
    }
}
