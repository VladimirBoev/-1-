package pretty;

public class UtilTests {
    JsonUtils jsonUtils = new JsonUtils();
    String jsonString = "{ \"name\": \"John\", \"age\": 30 ,\"city\": \"New York\" }";
    String jsonArrString = " [ { \"firstName \":\"John\",\"lastName\":\"Doe\",\"age\":30 },{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"age\":25} ]";

    public void tests() throws Exception {
        System.out.println("Цветной вывод json:");
        jsonUtils.colorPrettyPrettyJson(jsonString);
        System.out.println("Обычный вывод json:");
        jsonUtils.prettyPrintJson(jsonString);
        System.out.println("Цветной вывод json-массива: ");
        jsonUtils.colorPrettyPrettyJsonArray(jsonArrString);
        System.out.println("Обычный вывод json-массива: ");
        jsonUtils.prettyPrintArrayJson(jsonArrString);

    }


}
