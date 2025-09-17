import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Item> {
  private List<T> items = new ArrayList<>();

  public void addItem(T item) {
    items.add(item);
  }

  public void useAll() {
    for (T item : items) {
      item.use();
    }
    items.clear();
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public List<T> getItems() {
    return items;
  }
}
