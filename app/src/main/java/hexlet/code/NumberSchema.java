package hexlet.code;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema{
    public NumberSchema() {
        addPredicates(obj -> obj == null || obj instanceof Integer);
    }

    private void addPredicates(Predicate predicate) {
        predicates.add(predicate);
    }

    public void requiredI() {
        addPredicates(obj -> obj != null);
    }

    public void positive() {
        addPredicates(obj -> (int)obj > 0);
    }

    public void range(int indexStart, int indexEnd) {
        addPredicates(obj -> (int)obj >= indexStart && (int)obj <= indexEnd);
    }
}
