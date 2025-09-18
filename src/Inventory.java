import java.util.List;
import java.util.ArrayList;

public class Inventory<T extends Collectible> {
    private List<T> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory contains:");
            for (T item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }

    public void useAll() {
        if (items.isEmpty()) {
            System.out.println("Nothing to use.");
            return;
        }
        for (T item : items) {
            System.out.print("Using " + item.getName() + ": ");
            item.use();  
        }
        items.clear();
    }
}
