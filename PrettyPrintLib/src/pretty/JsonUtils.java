package pretty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {

    public void prettyPrintJson(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);
        StringBuilder builder = new StringBuilder();
        prettyPrintJson(json, builder, 0);
        System.out.println(builder);
    }

    public void prettyPrintArrayJson(String jsonStr){
        JSONArray json = new JSONArray(jsonStr);
        StringBuilder builder = new StringBuilder();
        prettyPrintArrayJson(json, builder, 0);
        System.out.println(builder);
    }

    private static void prettyPrintJson(JSONObject json, StringBuilder builder, int indentLevel) {
        Iterator<String> keys = json.keys();


        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);

            appendIndentedLine(builder, key + ": ", indentLevel);

            if (value instanceof JSONObject) {
                prettyPrintJson((JSONObject) value, builder, indentLevel + 1);
            } else if (value instanceof JSONArray) {
                prettyPrintArrayJson((JSONArray) value, builder, indentLevel + 1);
            } else {
                appendIndentedLine(builder, value.toString(), indentLevel + 1);
            }
        }

    }

    private static void prettyPrintArrayJson(JSONArray jsonArray, StringBuilder builder, int indentLevel) {
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);

            if (value instanceof JSONObject) {
                prettyPrintJson((JSONObject) value, builder, indentLevel + 1);
            } else if (value instanceof JSONArray) {
                prettyPrintArrayJson((JSONArray) value, builder, indentLevel + 1);
            } else {
                appendIndentedLine(builder, value.toString(), indentLevel);
            }
        }
    }

    private static void appendIndentedLine(StringBuilder builder, String content, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            builder.append("\t");
        }
        builder.append(content).append("\n");
    }

    public static Map<String, Object> convertJsonToMap(String json) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    value = convertJsonToMap(value.toString());
                }
                map.put(key, value);
            }
        } catch (JSONException e) {
            throw new Exception("Invalid JSON string: " + json, e);
        }
        return map;
    }

    public void colorPrettyPrettyJsonArray(String json) throws Exception {

        JSONArray jsonArray = new JSONArray(json);

        try {

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String jsstr = jsonObject.toString();
                Map<String, Object> map = convertJsonToMap(jsstr);
                prettyPrintMap(map);

            }
        } catch (JSONException e) {
            throw new Exception("Invalid JSON string: " + json, e);
        }

    }

    public static void prettyPrintMap(Map<String,Object> map){
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // choose color based on key name
            String colorKey = GREEN;
            String colorValue = RESET;
            System.out.println(colorKey + key + ": "+colorValue+value);
        }
    }

    public void colorPrettyPrettyJson(String json) throws Exception {
        Map<String,Object> map = convertJsonToMap(json);
        prettyPrintMap(map);
    }

}