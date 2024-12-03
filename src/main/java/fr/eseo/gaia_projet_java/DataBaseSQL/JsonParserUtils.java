package fr.eseo.gaia_projet_java.DataBaseSQL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParserUtils {

    private static final Gson gson = new Gson();

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = TypeToken.getParameterized(ArrayList.class, clazz).getType();
        return gson.fromJson(json, type);
    }

    // Convertit une chaîne JSON en List<Integer>
    public static List<Integer> parseJsonToListInt(String json) {
        Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    // Convertit une chaîne JSON en List<String>
    public static List<String> parseJsonToListString(String json) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    // Convertit une chaîne JSON en Map_controller<Integer, String>
    public static Map<Integer, String> parseJsonToMapIntString(String json) {
        Type mapType = new TypeToken<HashMap<Integer, String>>() {}.getType();
        return gson.fromJson(json, mapType);
    }

    public static Map<String, Integer> parseJsonToMapStringInt(String json) {
        Type mapType = new TypeToken<HashMap<String, Integer>>() {}.getType();
        return gson.fromJson(json, mapType);
    }


}
