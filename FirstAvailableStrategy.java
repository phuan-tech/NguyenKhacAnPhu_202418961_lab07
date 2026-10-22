import java.util.List;

public class FirstAvailableStrategy<T> implements ISelectionStrategy<T> {
    public T select(List<T> candidates) throws NoSuitableItemException {
        if (candidates == null || candidates.isEmpty()) {
            throw new NoSuitableItemException("Danh sách ứng viên không được rỗng");
        } else {
            return candidates.get(0);
        }
    }
}
