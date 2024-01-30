package hexlet.code;

public class Validator {
    private Object obj;

    public Validator() {
    }
    public Validator(Object obj) {
        this.obj = obj;
    }

    public StringSchema string() {
        return new StringSchema();
    }
}
