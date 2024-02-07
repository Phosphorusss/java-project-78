package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final List<Predicate> predicates = new ArrayList<>();

    public BaseSchema() {
    }

    public final void setPredicates(Predicate pr) {
        predicates.add(pr);
    }

    public final boolean isValid(T obj) {
        for (var predicate : predicates) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
