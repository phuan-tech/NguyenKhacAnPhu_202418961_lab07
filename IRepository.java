import java.util.function.Predicate;
import java.util.List;

public interface IRepository<T extends IIdentifiable> {
    void add(T item) throws DuplicateIdException;
    boolean remove(String id);
    T findById(String id);
    List<T> getAll();
    List<T> findAll(Predicate<T> condition);
}
