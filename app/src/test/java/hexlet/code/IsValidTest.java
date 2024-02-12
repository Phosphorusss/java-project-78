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


public final class IsValidTest {
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
    public void numberSchemaTest() {
        var expectedNumber = true;
        var actualNumber = numberSchema.isValid(null);
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
        var expectedString = true;
        var actualString = stringSchema.isValid(null);
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
