package hexlet.code;

public class App {
    public static void main(String[] args) {
        Validator validator = new Validator();
        //StringSchema st = validator.string();
        //st.minLength(2);
        //st.contains("i");
        //st.required();
        //System.out.println(st.isValid(null));
        //System.out.println(st.isValid("si"));

        NumberSchema ns = validator.number();
        ns.positive();
        ns.range(5, 10);
        ns.isValid(6);

    }
}
