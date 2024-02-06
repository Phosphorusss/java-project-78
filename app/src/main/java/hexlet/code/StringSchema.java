package hexlet.code;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        this.addPredicate(obj -> obj == null || obj instanceof String);
    }

    private void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public StringSchema minLength(int minLength) {
        addPredicate(obj -> obj.toString().length() > minLength);
        return this;
    }

    public StringSchema required() {
        addPredicate(obj -> obj != null && obj instanceof String && !obj.equals(""));
        return this;
    }

    public void contains(String substring) {
        addPredicate(obj -> obj.toString().contains(substring));
    }
}
