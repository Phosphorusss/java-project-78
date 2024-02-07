package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        setPredicates(obj -> obj == null || obj instanceof Integer);
    }

    public NumberSchema required() {
        setPredicates(obj -> obj != null);
        return this;
    }

    public NumberSchema positive() {
        setPredicates(obj -> obj == null || (Integer) obj > 0);
        return this;
    }

    public NumberSchema range(int indexStart, int indexEnd) {
        setPredicates(obj -> (Integer) obj >= indexStart && (Integer) obj <= indexEnd);
        return this;
    }
}
