import java.util.function.Predicate;
import java.util.List;

public class AllocationService<T extends TeachingDevice> {
    private final IRepository<T> repository;
    private ISelectionStrategy<T> strategy;

    public AllocationService(IRepository<T> repository, ISelectionStrategy<T> strategy) {
        this.repository = repository;
        this.strategy = strategy;
    }

    public void changeStrategy(ISelectionStrategy<T> newStrategy) {
        if (newStrategy == null) {
            throw new IllegalArgumentException();
        }
        this.strategy = newStrategy;
    }

    public T allocate(Predicate<T> additionalCondition) throws NoSuitableItemException, DeviceNotReadyException{
        List<T> candidates = this.repository.findAll(additionalCondition);
        T selectItem = strategy.select(candidates);
        selectItem.Borrow();
        return selectItem;
    }
}
