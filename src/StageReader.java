import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {

  public static Stage readStage(String path) {
    Stage stage = new Stage();
    List<String> lines;

    try {
      lines = Files.readAllLines(Paths.get(path));

      for (int lineNo = 0; lineNo < lines.size(); lineNo++) {
        String line = lines.get(lineNo).trim();
        if (line.isEmpty() || line.startsWith("#")) continue;

        int idx = line.indexOf('=');
        if (idx == -1) {
          throw new BadLineFormatException(lineNo + 1, "missing '=' in \"" + line + "\"");
        }

        char col = Character.toUpperCase(line.charAt(0));
        if (col < 'A' || col > 'Z') {
          throw new BadLineFormatException(lineNo + 1, "column '" + col + "' is non-alphabetic");
        }

        String rowStr = line.substring(1, idx);
        if (!rowStr.matches("\\d+")) {
          throw new BadLineFormatException(lineNo + 1, "row '" + rowStr + "' is non-numeric");
        }
        int row = Integer.parseInt(rowStr);

        String actor = line.substring(idx + 1).trim();

        if (col > 'T') {
          throw new OutOfBoundsException("col '" + col + "' is out of bounds");
        }
        if (row > 19) {
          throw new OutOfBoundsException("row '" + row + "' is out of bounds");
        }

        if (actor.equalsIgnoreCase("bird")) {
          stage.actors.add(new Bird(stage.grid.cellAtColRow(col, row).get()));
        } else if (actor.equalsIgnoreCase("cat")) {
          stage.actors.add(new Cat(stage.grid.cellAtColRow(col, row).get()));
        } else if (actor.equalsIgnoreCase("dog")) {
          stage.actors.add(new Dog(stage.grid.cellAtColRow(col, row).get()));
        } else {
          throw new UnknownActorException(actor);
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read file '" + path + "': " + e.getMessage());
      System.out.println("Creating default stage instead.");
      stage = defaultStage();
    } catch (StageFormatException e) {
      System.out.println("Stage file format error: " + e.getMessage());
      System.out.println("Creating default stage instead.");
      stage = defaultStage();
    }

    return stage;
  }

  private static Stage defaultStage() {
    Stage stage = new Stage();
    stage.actors.add(new Cat(stage.grid.cellAtColRow(0, 0).get()));
    stage.actors.add(new Dog(stage.grid.cellAtColRow(0, 15).get()));
    stage.actors.add(new Bird(stage.grid.cellAtColRow(12, 9).get()));
    return stage;
  }
}

class StageFormatException extends Exception {
  public StageFormatException(String message) {
    super(message);
  }
}

class BadLineFormatException extends StageFormatException {
  public BadLineFormatException(int lineNo, String details) {
    super("Line " + lineNo + " format error: " + details);
  }
}

class UnknownActorException extends StageFormatException {
  public UnknownActorException(String actor) {
    super("Unknown actor: '" + actor + "'");
  }
}

class OutOfBoundsException extends StageFormatException {
  public OutOfBoundsException(String message) {
    super(message);
  }
}

