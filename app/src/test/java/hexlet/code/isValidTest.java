package hexlet.code;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class isValidTest {
    private Validator validator;
    private StringSchema st;

    @BeforeEach
    public void beforeEach () {
        validator = new Validator();
        st = validator.string();
    }

    @Test
    public void isValidNull() {
        var expected = true;
        var actual = st.isValid(null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidNotNull() {
        var expected = false;
        st.required();
        var actual = st.isValid(null);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidMinLength() {
        var expected = true;
        st.minLength(2);
        var actual = st.isValid("testString");
        assertEquals(expected, actual);
    }

    @Test
    public void isValidContains() {
        var expected = true;
        st.contains("Str");
        var actual = st.isValid("testString");
        assertEquals(expected, actual);
    }

    @Test
    public void isValidAll() {
        var expected = true;
        st.contains("Str");
        st.minLength(3);
        st.required();
        var actual = st.isValid("testString");
        assertEquals(expected, actual);
    }
}
