package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private List<Predicate> predicates = new ArrayList<>();

    public BaseSchema() {
    }

    public BaseSchema(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public void setPredicates(Predicate pr) {
        predicates.add(pr);
        //this.predicates = predicates;
    }

    public boolean isValid(T obj) {
        for (var predicate : predicates) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
