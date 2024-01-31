package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class IsValidTest {
    private Validator validator;
    private StringSchema st;
    private NumberSchema ns;
    private MapSchema ms;
    private Map map;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        st = validator.string();
        ns = validator.number();
        ms = validator.map();

        map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
    }

    @Test
    public void isValidNull() {
        var expectedString = true;
        var actualString = st.isValid(null);
        assertEquals(expectedString, actualString);

        var expectedNumber = true;
        var actualNumber = ns.isValid(null);
        assertEquals(expectedNumber, actualNumber);

        var expectedMap = true;
        var actualMap = ns.isValid(null);
        assertEquals(expectedMap, actualMap);

    }

    @Test
    public void isValidNotNull() {
        var expectedString = false;
        st.required();
        var actualString = st.isValid(null);
        assertEquals(expectedString, actualString);

        var expectedNumber = false;
        ns.requiredI();
        var actualNumber = ns.isValid(null);
        assertEquals(expectedNumber, actualNumber);

        var expectedMap = false;
        ms.required();
        var actualMap = ns.isValid(null);
        assertEquals(expectedMap, actualMap);
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

    @Test
    public void isValidPositive() {
        var expectedNumber = false;
        ns.positive();
        var actualNumber = st.isValid(-5);
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void isValidRange() {
        var expectedNumber = true;
        ns.range(5, 10);
        var actualNumber = ns.isValid(5);
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void isValidSizeof() {
        var expectedMap = true;
        ms.sizeof(2);
        var actualMap = ms.isValid(map);
        assertEquals(expectedMap, actualMap);
    }
}
