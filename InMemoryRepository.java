import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class InMemoryRepository<T extends IIdentifiable> implements IRepository<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item không được null");
        }

        this.items.add(item);
    };

    public boolean remove(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Mã không hợp lệ");
        }

        for (T item : this.items) {
            if (item.getId().equals(id)) {
                this.items.remove(item);
                System.out.println("Đã xóa thiết bị " + id);
                return true;
            }
        }
        System.out.println("Thiết bị không tồn tại, không thể thực hiện yêu cầu");
        return false;
    };
    
    public T findById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Mã không hợp lệ");
        }

        for (T item : this.items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        System.out.println("Không tìm thấy thiết bị " + id);
        return null;
    };

    public List<T> getAll() {
        return new ArrayList<>(this.items);
    };

    public List<T> findAll(Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T item : this.items) {
            if (condition.test(item)) {
                result.add(item);
            }
        }

        return result;
    };
}
