import java.util.function.ToDoubleFunction;
import java.util.List;

public class HighestPriorityStrategy<T> implements ISelectionStrategy<T> {
    private final ToDoubleFunction<T> scoreSelector;

    public HighestPriorityStrategy(ToDoubleFunction<T> scoreSelector) {
        this.scoreSelector = scoreSelector;
    }

    public T select(List<T> candidates) throws NoSuitableItemException {
        if (candidates == null || candidates.isEmpty()) {
            throw new NoSuitableItemException("Không có thiết bị phù hợp");
        }
        T bestItem = candidates.get(0);
        double highestScore = this.scoreSelector.applyAsDouble(bestItem);

        for (T item : candidates) {
            double currentScore = this.scoreSelector.applyAsDouble(item);
            if (currentScore > highestScore) {
                highestScore = currentScore;
                bestItem = item;
            }
        }

        return bestItem;
    }
}
