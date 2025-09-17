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
    String entity;

    try {
      lines = Files.readAllLines(Paths.get(path));

      
      for (String line : lines) {
        idx = line.indexOf('=');
        if (idx == -1) {
          throw new FormatException("missing equals sign.");
        }

       
        col = Character.toUpperCase(line.charAt(0));
        if (col < 'A' || col > 'T') {
          throw new FormatException("column '" + col + "' is out of bounds.");
        }

       
        for (int i = 1; i < idx; i++) {
          if (!Character.isDigit(line.charAt(i))) {
            throw new FormatException("row '" + line.substring(1, idx) + "' is non-numeric.");
          }
        }
        row = Integer.parseInt(line.substring(1, idx));
        if (row < 0 || row > 19) {
          throw new IndexOutOfBoundsException("row '" + row + "' is out of bounds.");
        }

        entity = line.substring(idx + 1).toLowerCase();

       
        if (entity.equals("bird")) {
          stage.actors.add(new Bird(stage.grid.cellAtColRow(col, row).get()));
        } else if (entity.equals("cat")) {
          stage.actors.add(new Cat(stage.grid.cellAtColRow(col, row).get()));
        } else if (entity.equals("dog")) {
          stage.actors.add(new Dog(stage.grid.cellAtColRow(col, row).get()));

        
        } else if (entity.equals("fish")) {
          stage.grid.cellAtColRow(col, row).get().addItem(new Fish());
        } else if (entity.equals("bone")) {
          stage.grid.cellAtColRow(col, row).get().addItem(new Bone());
        } else if (entity.equals("seed")) {
          stage.grid.cellAtColRow(col, row).get().addItem(new Seed());

        } else {
          throw new FormatException("unknown entity '" + entity + "'");
        }
      }

    } catch (IOException | FormatException e) {
      System.out.println("Error reading '" + path + "', creating default stage.");
      stage = new Stage();
      stage.actors.add(new Cat(stage.grid.cellAtColRow(0, 0).get()));
      stage.actors.add(new Dog(stage.grid.cellAtColRow(0, 15).get()));
      stage.actors.add(new Bird(stage.grid.cellAtColRow(12, 9).get()));

      
      stage.grid.cellAtColRow(5, 5).get().addItem(new Fish());
      stage.grid.cellAtColRow(10, 10).get().addItem(new Bone());
      stage.grid.cellAtColRow(15, 15).get().addItem(new Seed());
    }

    return stage;
  }
}

class FormatException extends Exception {
  public FormatException(String message) {
    super("Stage file syntax error: " + message);
  }
}
