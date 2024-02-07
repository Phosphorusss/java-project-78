package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        setPredicates(obj -> obj == null || obj instanceof Map<?, ?>);
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        setPredicates(validatedMap -> schemas.entrySet().stream().allMatch(schemaMap -> {
            var value = ((Map<?, ?>) validatedMap).get(schemaMap.getKey());
            return schemaMap.getValue().isValid((String) value);
        }));
        return this;
    }

    public MapSchema required() {
        setPredicates(obj -> obj != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        setPredicates(obj -> ((Map<?, ?>) obj).size() == size);
        return this;
    }
}
