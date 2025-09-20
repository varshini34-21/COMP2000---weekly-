import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
    Grid grid;
    private List<Actor> listOfPlayers;
    List<Cell> cellOverlay;
    Optional<Actor> playerInAction;

    enum State {ChoosingActor, SelectingNewLocation}
    State currentState;

    public Stage() {
        grid = new Grid();
        listOfPlayers = new ArrayList<>();
        cellOverlay = new ArrayList<>();
        playerInAction = Optional.empty();
        currentState = State.ChoosingActor;

        // Add your players
        Dog dog = new Dog(grid.cellAtColRow('B', 2).get());
        Cat cat = new Cat(grid.cellAtColRow('F', 5).get());
        Bird bird = new Bird(grid.cellAtColRow('J', 10).get());

        listOfPlayers.add(dog);
        listOfPlayers.add(cat);
        listOfPlayers.add(bird);

        // Add some items
        grid.cellAtColRow('C', 3).get().addItem(new Bone());
        grid.cellAtColRow('G', 7).get().addItem(new Fish());
        grid.cellAtColRow('L', 12).get().addItem(new Seed());
    }

    public void paint(Graphics g, Point mouseLoc) {
        // Draw grid
        grid.paint(g, mouseLoc);

        // Blue overlay highlight
        grid.paintOverlay(g, cellOverlay, new Color(0f, 0f, 1f, 0.5f));

        // Draw players
        for (Actor player : listOfPlayers) {
            player.paint(g);
        }
    }

    public List<Cell> getClearRadius(Cell from, int size) {
        List<Cell> init = grid.getRadius(from, size);
        for (Actor player : listOfPlayers) {
            init.remove(player.loc); // don’t allow overlap
        }
        return init;
    }

    public void mouseClicked(int x, int y) {
        switch (currentState) {
            case ChoosingActor:
                playerInAction = Optional.empty();
                for (Actor player : listOfPlayers) {
                    if (player.loc.contains(new Point(x, y))) {
                        // highlight movement radius (2 steps)
                        cellOverlay = grid.getRadius(player.loc, 2);
                        playerInAction = Optional.of(player);
                        currentState = State.SelectingNewLocation;
                    }
                }
                break;

            case SelectingNewLocation:
                Optional<Cell> clicked = Optional.empty();
                for (Cell c : cellOverlay) {
                    if (c.contains(new Point(x, y))) {
                        clicked = Optional.of(c);
                    }
                }
                cellOverlay = new ArrayList<>();
                if (clicked.isPresent() && playerInAction.isPresent()) {
                    Cell moveTo = clicked.get();
                    Actor actor = playerInAction.get();

                    // Terrain rules: only Bird can enter water
                    if (moveTo instanceof EnterRule) {
                        if (!((EnterRule) moveTo).canEnter(actor)) {
                            System.out.println(actor.getClass().getSimpleName() + " cannot enter here.");
                            currentState = State.ChoosingActor;
                            return;
                        }
                    }

                    // Move actor
                    actor.loc = moveTo;
                    actor.updateShape();
                    actor.pickUpItems(moveTo);
                }
                currentState = State.ChoosingActor;
                break;
        }
    }
}
