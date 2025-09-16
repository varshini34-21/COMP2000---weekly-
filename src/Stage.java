import java.awt.Graphics;
import java.util.ArrayList;

public class Stage {
    private Grid grid;
    private ArrayList<Actor> actors;

    public Stage(Grid grid) {
        this.grid = grid;
        this.actors = new ArrayList<>();
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void paint(Graphics g) {
        grid.paint(g);

        
        for (Actor actor : actors) {
            actor.paint(g);
        }
    }
}
