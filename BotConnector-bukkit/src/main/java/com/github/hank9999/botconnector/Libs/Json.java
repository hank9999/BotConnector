package com.github.hank9999.botconnector.Libs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static String Serialization(Object o) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(o);
    }
}
