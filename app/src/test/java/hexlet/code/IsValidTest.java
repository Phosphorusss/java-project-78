package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class IsValidTest {
    private Validator validator;
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapSchema mapSchema;
    private Map map;
    private Map human1;
    private Map human2;
    private Map human3;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
        mapSchema = validator.map();

        map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
    }

    @Test
    public void isValidNull() {
        var expectedString = true;
        var actualString = stringSchema.isValid(null);
        assertEquals(expectedString, actualString);

        var expectedNumber = true;
        var actualNumber = numberSchema.isValid(null);
        assertEquals(expectedNumber, actualNumber);

        var expectedMap = true;
        var actualMap = numberSchema.isValid(null);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void isValidNotNull() {
        var expectedString = false;
        stringSchema.required();
        var actualString = stringSchema.isValid(null);
        assertEquals(expectedString, actualString);

        var expectedNumber = false;
        numberSchema.required();
        var actualNumber = numberSchema.isValid(null);
        assertEquals(expectedNumber, actualNumber);

        var expectedMap = false;
        mapSchema.required();
        var actualMap = numberSchema.isValid(null);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void isValidMinLength() {
        var expected = true;
        stringSchema.minLength(2);
        var actual = stringSchema.isValid("testString");
        assertEquals(expected, actual);
    }

    @Test
    public void isValidContains() {
        var expected = true;
        stringSchema.contains("Str");
        var actual = stringSchema.isValid("testString");
        assertEquals(expected, actual);
    }

    @Test
    public void isValidAll() {
        var expected = true;
        stringSchema.contains("Str");
        stringSchema.minLength(3);
        stringSchema.required();
        var actual = stringSchema.isValid("testString");
        assertEquals(expected, actual);
    }

    @Test
    public void isValidPositive() {
        var expectedNumber = false;
        numberSchema.positive();
        var actualNumber = stringSchema.isValid(-5);
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void isValidPositiveNull() {
        var expectedNumber = false;
        numberSchema.positive();
        var actualNumber = numberSchema.isValid(null);
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void isValidRange() {
        var expectedNumber = true;
        numberSchema.range(5, 10);
        var actualNumber = numberSchema.isValid(5);
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void isValidSizeof() {
        var expectedMap = true;
        mapSchema.sizeof(2);
        var actualMap = mapSchema.isValid(map);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void isValidValueRequired() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);
        var expected = true;
        var actual = mapSchema.isValid(human1);
        assertEquals(expected, actual);
    }

    @Test
    public void isValidValueMinLength() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);
        var expected = false;
        var actual = mapSchema.isValid(human3);
        assertEquals(expected, actual);
    }
}
