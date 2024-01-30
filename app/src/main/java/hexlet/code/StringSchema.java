package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    public boolean schema;
    List<Predicate> predicates = new ArrayList<>();

    public StringSchema() {
        this.addPredicate(obj -> obj == null || obj instanceof String);
    }

    private void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public void minLength(int minLength) {
        addPredicate(obj -> obj.toString().length() > minLength);
        System.out.println("run");
    }

    public void required() {
        addPredicate(obj -> obj != null && obj instanceof String && !obj.equals(""));
    }

    public void contains(String substring) {
        addPredicate(obj -> obj.toString().contains(substring));
    }

    public boolean isValid(String obj) {
        for (var predicate : predicates) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
