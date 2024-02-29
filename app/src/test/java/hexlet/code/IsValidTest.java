package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public final class IsValidTest {
    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
    }

    @Test
    public void numberSchemaTest() {
        var schema = validator.number();

        assertTrue(schema.isValid(6));
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid(6));
        assertTrue(schema.isValid(-4));

        schema.positive();
        assertFalse(schema.isValid(-4));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
    }

    @Test
    public void stringSchemaTest() {
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("what does the fox say"));

        schema.minLength(2);
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("h"));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    public void mapSchemaTest() {
        var schema = validator.map();
        var map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(map));

        schema.sizeof(2);
        assertTrue(schema.isValid(map));
    }

    @Test
    public void mapSchemaShapeTest() {
        var schema = validator.map();

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
        schema.shape(schemas);

        assertTrue(schema.isValid(human1));
        assertFalse(schema.isValid(human2));
        assertFalse(schema.isValid(human3));
    }
}
