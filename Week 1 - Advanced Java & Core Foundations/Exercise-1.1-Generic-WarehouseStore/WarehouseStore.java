import java.util.*;

public class WarehouseStore<T extends WarehouseItem> {
    private List<T> items = new ArrayList<>();
    private Map<String, T> idMap = new HashMap<>();

    public void addItem(T item) {
        items.add(item);
        idMap.put(item.getId(), item);
    }

    public boolean removeItem(String id) {
        T removed = idMap.remove(id);
        if (removed != null) {
            items.remove(removed);
            return true;
        }
        return false;
    }

    public List<T> findByCategory(String category) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }

    public int size() { return items.size(); }
}
