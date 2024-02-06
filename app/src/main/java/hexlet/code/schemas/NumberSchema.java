package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addPredicates(obj -> obj == null || obj instanceof Integer);
    }

    private NumberSchema addPredicates(Predicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public NumberSchema required() {
        addPredicates(obj -> obj != null);
        return this;
    }

    public NumberSchema positive() {
        addPredicates(obj -> obj == null || (Integer) obj > 0);
        return this;
    }

    public NumberSchema range(int indexStart, int indexEnd) {
        addPredicates(obj -> (Integer) obj >= indexStart && (Integer) obj <= indexEnd);
        return this;
    }
}
