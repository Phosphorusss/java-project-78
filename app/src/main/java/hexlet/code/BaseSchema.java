package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema <T> {
    List<Predicate> predicates = new ArrayList<>();
    public boolean isValid(T obj) {
        for (var predicate : predicates) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
