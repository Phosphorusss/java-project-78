package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addPredicate(obj -> obj == null || obj instanceof String);
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
