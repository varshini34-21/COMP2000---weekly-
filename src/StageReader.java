import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {
  public static Stage readStage(String path) {
    Stage stage = new Stage();
    List<String> lines;
    int idx;
    char col;
    int row;
    String actor;

    try {
      // read all lines from file into list
      lines = Files.readAllLines(Paths.get(path));

      // format is Crr=aaaa where C is column, r is row, and a is actor
      for(String line: lines) {
        // split on equals-sign
        idx = line.indexOf('=');
        if(idx == -1) {
          throw new FormatException("missing equals sign.");
        } else {

          // grab column character ensuring it's upper-case
          col = Character.toUpperCase(line.charAt(0));
          if (col >= 'A' && col <= 'Z') {
          } else {
            throw new FormatException("column '" + String.valueOf(col) + "' is non-alpabetic.");
          }

          // characters between the column identifier and the equals-sign are the row number
          for(int i=1; i<idx; i++) {
            if(line.charAt(i) < '0' || line.charAt(i) > '9') {
              throw new FormatException("row '" + line.substring(i, idx) + "' is non-numeric.");
            }
          }
          row = Integer.parseInt(line.substring(1, idx));
          actor = line.substring(idx+1, line.length());
        }

        // Ensure that Col and Row is not greater than the size of the Grid
        if(col > 'T') {
          throw new IndexOutOfBoundsException("col '" + String.valueOf(col) + "' is out of bounds.");
        }
        if(row > 19) {
          throw new IndexOutOfBoundsException("row '" + String.valueOf(row) + "' is out of bounds.");
        }
        // hard-wiring these is definitely a code smell
        // we will see a better way of doing this later
        if(actor.equalsIgnoreCase("bird")) {
          stage.actors.add(new Bird(stage.grid.cellAtColRow(col, row).get()));
        } else if(actor.equalsIgnoreCase("cat")) {
          stage.actors.add(new Cat(stage.grid.cellAtColRow(col, row).get()));
        } else if(actor.equalsIgnoreCase("dog")) {
          stage.actors.add(new Dog(stage.grid.cellAtColRow(col, row).get()));
        } else {
          throw new FormatException(" actor '" + actor + "' unknown.");
        }
      }
    } catch (IOException | FormatException e) {
      // if any error occurs, create a blank stage and add actors in default locations
      System.out.println("Error reading '" + path + "', creating default stage.");
      stage = new Stage();
      stage.actors.add(new Cat(stage.grid.cellAtColRow(0, 0).get()));
      stage.actors.add(new Dog(stage.grid.cellAtColRow(0, 15).get()));
      stage.actors.add(new Bird(stage.grid.cellAtColRow(12, 9).get()));
    }
    return stage;
  }
}

class FormatException extends Exception {
  public FormatException() {
    super("Stage file syntax error.");
  }

  public FormatException(String message) {
    super("Stage file syntax error: " + message);
  }

  public FormatException(String message, Throwable cause) {
    super("Stage file syntax error: " + message, cause);
  }
}
