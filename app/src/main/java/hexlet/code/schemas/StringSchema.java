package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        this.addPredicate(obj -> obj == null || obj instanceof String);
    }

    private StringSchema addPredicate(Predicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public StringSchema minLength(int minLength) {
        addPredicate(obj -> obj.toString().length() > minLength);
        return this;
    }

    public StringSchema required() {
        addPredicate(obj -> obj != null && obj instanceof String && !obj.equals(""));
        return this;
    }

    public StringSchema contains(String substring) {
        addPredicate(obj -> obj.toString().contains(substring));
        return this;
    }
}
