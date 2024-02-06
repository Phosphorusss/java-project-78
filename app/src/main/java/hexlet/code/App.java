package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        Validator validator = new Validator();
        var schema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        //System.out.println(schemas);
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", null);
        System.out.println(schema.isValid(human1));


        /*var validator1 = new Validator();
        var x = validator1.map();
        var data = new HashMap<String, String>();
        x.sizeof(2);
        schema.isValid(data);
        data.put("key2", "value2");
        schema.isValid(data);*/



    }
}
