import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Dog extends Actor {
    public Dog(Cell inLoc) {
        super(inLoc, Color.BLUE, createShape(inLoc));
    }

    private static ArrayList<Polygon> createShape(Cell loc) {
        ArrayList<Polygon> display = new ArrayList<>();

        Polygon face = new Polygon();
        face.addPoint(loc.x + 8, loc.y + 7);
        face.addPoint(loc.x + 27, loc.y + 7);
        face.addPoint(loc.x + 27, loc.y + 25);
        face.addPoint(loc.x + 8, loc.y + 25);
        display.add(face);

        Polygon ear1 = new Polygon();
        ear1.addPoint(loc.x + 5, loc.y + 5);
        ear1.addPoint(loc.x + 15, loc.y + 5);
        ear1.addPoint(loc.x + 5, loc.y + 15);
        display.add(ear1);

        Polygon ear2 = new Polygon();
        ear2.addPoint(loc.x + 20, loc.y + 5);
        ear2.addPoint(loc.x + 30, loc.y + 5);
        ear2.addPoint(loc.x + 30, loc.y + 15);
        display.add(ear2);

        return display;
    }

    @Override
    public void updateShape() {
        this.display = createShape(loc);
    }
}
