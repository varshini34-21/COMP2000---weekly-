import java.util.List;

public interface BotMover {
  public Cell chooseNextLoc(List<Cell> possibleLocs, Actor currActor, List<Actor> otherActors);
}
