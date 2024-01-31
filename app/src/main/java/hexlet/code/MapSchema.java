package hexlet.code;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addPredicate(obj -> obj == null || obj instanceof Map<?, ?>);
    }

    private void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public void required() {
        addPredicate(obj -> obj != null);
    }

    public void sizeof(int size) {
        addPredicate(obj -> ((Map<?, ?>) obj).size() == size);
    }
}
