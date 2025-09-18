import java.util.ArrayList;
import java.util.List;

public class Cell {
    public int x, y;
    private List<Collectible> items;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.items = new ArrayList<>();
    }

   
    public void addItem(Collectible item) {
        items.add(item);
    }

   
    public List<Collectible> collectItems() {
        List<Collectible> temp = new ArrayList<>(items);
        items.clear(); 
        return temp;
    }

   
    public boolean hasItems() {
        return !items.isEmpty();
    }
}
