import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageReader {
    public static Stage readStage(String filename) {
        Stage stage = new Stage();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            System.out.println("Loaded stage file: " + filename);
            for (String line : lines) {
                System.out.println(line);
                
            }
        } catch (IOException e) {
            System.out.println("Stage file not found, using default stage.");
        }
        return stage;
    }
}
