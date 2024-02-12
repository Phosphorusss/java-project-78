package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addPredicate(obj -> obj == null || obj instanceof Integer);
    }

    public NumberSchema required() {
        addPredicate(obj -> obj != null);
        return this;
    }

    public NumberSchema positive() {
        addPredicate(obj -> obj == null || (Integer) obj > 0);
        return this;
    }

    public NumberSchema range(int indexStart, int indexEnd) {
        addPredicate(obj -> (Integer) obj >= indexStart && (Integer) obj <= indexEnd);
        return this;
    }
}
