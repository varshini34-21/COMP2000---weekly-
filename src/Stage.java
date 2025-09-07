import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Stage {
  Grid grid;
  private List<Actor> listOfPlayers;
  List<Cell> cellOverlay;
  Optional<Actor> playerInAction;

  enum State {ChoosingActor, SelectingNewLocation, BotMoving}
  State currentState;
  Beat beat;

  public Stage() {
    grid = new Grid();
    listOfPlayers = new ArrayList<Actor>();
    cellOverlay = new ArrayList<Cell>();
    playerInAction = Optional.empty();
    currentState = State.ChoosingActor;
    beat = new AnimationBeat();
  }

  public void addPlayer(Actor player) {
    listOfPlayers.add(player);
    if(player.isBot()) {
      beat.punchIn(player);
    }
  }

  public void paint(Graphics g, Point mouseLoc) {
    // do we have bot moves to make?
    if(currentState == State.BotMoving) {
      for(Actor player: listOfPlayers) {
        if(player.isBot()) {
          List<Cell> possibleLocs = getClearRadius(player.loc, player.moves);
          int moveBotChooses = (new Random()).nextInt(possibleLocs.size());
          player.setLocation(possibleLocs.get(moveBotChooses));
        }
      }
      currentState = State.ChoosingActor;
      for(Actor player: listOfPlayers) {
        player.turns = 1;
      }
    }

    grid.paint(g, mouseLoc);
    // Blue cell selection overlay with 50% transparency
    grid.paintOverlay(g, cellOverlay, new Color(0f, 0f, 1f, 0.5f));

    beat.ticktock();
    for(Actor player: listOfPlayers) {
      player.paint(g);
    }

    // lots of magic numbers here
    // they are used to calculate the coordinates of where to draw on the information panel
    final int hTab = 10;
    final int blockVT = 35;
    final int margin = 21*blockVT;
    int yLoc = 20;

    // state display
    g.setColor(Color.DARK_GRAY);
    g.drawString(currentState.toString(), margin, yLoc);
    yLoc = yLoc + blockVT;
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      String coord = String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row);
      g.drawString(coord, margin, yLoc);
    }

    // agent display
    final int vTab = 15;
    final int labelIndent = margin + hTab;
    final int valueIndent = margin + 3*blockVT;
    yLoc = yLoc + 2*blockVT;
    for(int i = 0; i < listOfPlayers.size(); i++){
      Actor a = listOfPlayers.get(i);
      yLoc = yLoc + 2*blockVT;
      g.drawString(a.getClass().getName(), margin, yLoc);
      g.drawString("location:", labelIndent, yLoc+vTab);
      g.drawString(Character.toString(a.loc.col) + Integer.toString(a.loc.row), valueIndent, yLoc+vTab);
      g.drawString("player type:", labelIndent, yLoc+2*vTab);
      g.drawString(a.isBot() ? "Bot" : "Human", valueIndent, yLoc+2*vTab);
      if(a.isBot() && a.mover != null) {
        g.drawString("mover:", labelIndent, yLoc+3*vTab);
        g.drawString(a.mover.getClass().getName(), valueIndent, yLoc+3*vTab);
      }
    }    
  }

  public List<Cell> getClearRadius(Cell from, int size) {
    List<Cell> init = grid.getRadius(from, size);
    for(Actor player: listOfPlayers) {
      init.remove(player.loc);
    }
    return init;
  }

  public void mouseClicked(int x, int y) {
    switch(currentState) {
      case ChoosingActor:
        playerInAction = Optional.empty();
        for(Actor player: listOfPlayers) {
          if(player.loc.contains(x, y) && !player.isBot()) {
            cellOverlay = grid.getRadius(player.loc, player.moves);
            playerInAction = Optional.of(player);
            currentState = State.SelectingNewLocation;
          }
        }
        break;
      case SelectingNewLocation:
        Optional<Cell> clicked = Optional.empty();
        for(Cell c: cellOverlay) {
          if(c.contains(x, y)) {
            clicked = Optional.of(c);
          }
        }
        cellOverlay = new ArrayList<Cell>();
        if(clicked.isPresent() && playerInAction.isPresent()) {
          playerInAction.get().setLocation(clicked.get());
          playerInAction.get().turns--;
          int humansWithMovesLeft = 0;
          for(Actor player: listOfPlayers) {
            if(!player.isBot() && player.turns > 0) {
              humansWithMovesLeft++;
            }
          }
          if(humansWithMovesLeft > 0) {
            currentState = State.ChoosingActor;
          } else {
            currentState = State.BotMoving;
          }
        }
        break;
      default:
        // this state should never be reached
        System.out.println(currentState);
        break;
    }
  }
}
