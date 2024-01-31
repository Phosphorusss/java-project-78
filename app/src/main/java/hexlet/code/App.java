package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Validator validator = new Validator();
        //StringSchema st = validator.string();
        //st.minLength(2);
        //st.contains("i");
        //st.required();
        //System.out.println(st.isValid(null));
        //System.out.println(st.isValid("si"));

        /*NumberSchema ns = validator.number();
        ns.positive();
        ns.range(5, 10);
        ns.isValid(6);*/

        MapSchema map = validator.map();
        Map<String, String> m = new HashMap<>();
        //m.put(null, null);
        //System.out.println(m);
        m.put("key1", "value1");
        //m.put("key2", "value2");
        System.out.println(m);
        map.sizeof(2);
        System.out.println(map.isValid(m));

    }
}
