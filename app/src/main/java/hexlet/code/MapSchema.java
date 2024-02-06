package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addPredicate(obj -> obj == null || obj instanceof Map<?, ?>);
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        addPredicate(validatedMap -> schemas.entrySet().stream().allMatch(schemaMap -> {
            var value = ((Map<?,?>) validatedMap).get(schemaMap.getKey());
            return schemaMap.getValue().isValid((String) value);
        }));
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
