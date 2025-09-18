import java.util.List;

public class StageReader {
    private Stage stage;

    public StageReader(Stage stage) {
        this.stage = stage;
    }

    public void printStageInfo() {
        List<Actor> actors = stage.getActors();
        Cell[][] grid = stage.getGrid();

        System.out.println("Stage has " + actors.size() + " actors.");
        for (Actor actor : actors) {
            System.out.println("- " + actor.getClass().getSimpleName() + " at cell (" + actor.loc.x + "," + actor.loc.y + ")");
        }

        System.out.println("Grid size: " + grid.length + " x " + grid[0].length);
    }
}
