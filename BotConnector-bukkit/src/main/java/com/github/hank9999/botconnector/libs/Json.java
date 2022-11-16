package com.github.hank9999.botconnector.libs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    public static String Serialization(Object o) {
        return gson.toJson(o);
    }
}
