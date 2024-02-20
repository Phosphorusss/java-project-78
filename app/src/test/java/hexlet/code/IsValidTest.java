package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public final class IsValidTest {
    private Validator validator;
    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
    }

    @Test
    public void numberSchemaTest() {
        var numberSchema = validator.number();

        var expectedNumber = true;
        var actualNumber = numberSchema.isValid(null);
        numberSchema.positive();
        numberSchema.range(5, 10);
        assertEquals(expectedNumber, actualNumber);

        var expected = true;
        numberSchema.required();
        numberSchema.positive();
        numberSchema.range(5, 10);
        var actual = numberSchema.isValid(5);
        assertEquals(expected, actual);
    }

    @Test
    public void stringSchemaTest() {
        var stringSchema = validator.string();

        var expectedString = true;
        var actualString = stringSchema.isValid(null);
        stringSchema.minLength(2);
        stringSchema.contains("Str");
        assertEquals(expectedString, actualString);

        var expected = true;
        stringSchema.required();
        stringSchema.minLength(2);
        stringSchema.contains("Str");
        var actual = stringSchema.isValid("testString");
        assertEquals(expected, actual);
    }

    @Test
    public void mapSchemaTest() {
        var mapSchema = validator.map();
        var map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        var expectedMap = true;
        var actualMap = mapSchema.isValid(null);
        assertEquals(expectedMap, actualMap);

        var expected = true;
        mapSchema.required();
        mapSchema.sizeof(2);
        var actual = mapSchema.isValid(map);
        assertEquals(expected, actual);
    }

    @Test
    public void mapSchemaShapeTest() {
        var mapSchema = validator.map();
        var human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        var human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        var human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);
        var expected = true;
        var actual = mapSchema.isValid(human1);
        assertEquals(expected, actual);

        var expectedNotNull = false;
        var actualNotNull = mapSchema.isValid(human2);
        assertEquals(expectedNotNull, actualNotNull);

        var expectedMinLength = false;
        var actualMinLength = mapSchema.isValid(human3);
        assertEquals(expectedMinLength, actualMinLength);
    }
}
