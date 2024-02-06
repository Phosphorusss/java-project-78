package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addPredicate(obj -> obj == null || obj instanceof Map<?, ?>);
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addPredicate(validatedMap -> schemas.entrySet().stream().allMatch(schemaMap -> {
            var value = ((Map<?, ?>) validatedMap).get(schemaMap.getKey());
            return schemaMap.getValue().isValid((String) value);
        }));
        return this;
    }

    private MapSchema addPredicate(Predicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public MapSchema required() {
        addPredicate(obj -> obj != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addPredicate(obj -> ((Map<?, ?>) obj).size() == size);
        return this;
    }
}
