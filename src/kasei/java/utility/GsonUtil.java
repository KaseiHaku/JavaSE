package kasei.utility;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GsonUtil {

    public static void test(){
        Object obj = new Object();
        Gson gson = new Gson();
        gson.toJson(obj);


        gson.fromJson("", Object.class);
    }

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>() ;
        map.put("a", "adf");
        map.put("int", 12);
        map.put("double", 212.3);
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));


        String jsonStr =
                "{" +
                        "\"tty\":\"qwf低分段rt\"," +
                        "\"gg\":123" +
                        "}";


        Gson gson2 = new GsonBuilder()
                .registerTypeAdapter(
                        new TypeToken<Map<String, Object>>() {
                        }.getType(),
                        new JsonDeserializer<Map<String, Object>>() {
                            @Override
                            public Map<String, Object> deserialize(
                                    JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {

                                Map<String, Object> treeMap = new HashMap<String, Object>();
                                JsonObject jsonObject = json.getAsJsonObject();
                                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                                for (Map.Entry<String, JsonElement> entry : entrySet) {
                                    treeMap.put(entry.getKey(), entry.getValue());
                                }
                                return treeMap;
                            }
                        }).create();



        Map map1 = gson2.fromJson(jsonStr, Map.class);
        Map map2 = gson2.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {}.getType());

        System.out.println(gson.toJson(map1));
        System.out.println(gson.toJson(map2));

    }
}
