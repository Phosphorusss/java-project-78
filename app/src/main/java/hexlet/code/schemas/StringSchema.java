package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        setPredicates(obj -> obj == null || obj instanceof String);
    }

    public StringSchema minLength(int minLength) {
        setPredicates(obj -> obj.toString().length() > minLength);
        return this;
    }

    public StringSchema required() {
        setPredicates(obj -> obj != null && obj instanceof String && !obj.equals(""));
        return this;
    }

    public StringSchema contains(String substring) {
        setPredicates(obj -> obj.toString().contains(substring));
        return this;
    }
}
