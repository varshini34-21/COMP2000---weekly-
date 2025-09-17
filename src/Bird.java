import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Bird extends Actor {
    public static final int birbMoves = 3;

    public Bird(Cell inLoc, boolean isBot) {
        super(inLoc, Color.GREEN, isBot, birbMoves);
    }

    @Override
    protected void setPoly() {
        display = new ArrayList<>();

        int x = loc.getX();
        int y = loc.getY();

        Polygon wing1 = new Polygon();
        wing1.addPoint(x + 5, y + 5);
        wing1.addPoint(x + 15, y + 17);
        wing1.addPoint(x + 5, y + 17);
        display.add(wing1);

        Polygon wing2 = new Polygon();
        wing2.addPoint(x + 30, y + 5);
        wing2.addPoint(x + 20, y + 17);
        wing2.addPoint(x + 30, y + 17);
        display.add(wing2);

        Polygon body = new Polygon();
        body.addPoint(x + 15, y + 10);
        body.addPoint(x + 20, y + 10);
        body.addPoint(x + 20, y + 25);
        body.addPoint(x + 15, y + 25);
        display.add(body);
    }
}

