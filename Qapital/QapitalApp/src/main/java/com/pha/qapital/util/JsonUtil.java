package com.pha.qapital.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import timber.log.Timber;

/**
 * Created by pha on 2017-12-03.
 */

public class JsonUtil {

    public static final String EMPTY_JSON = "{}";

    private static Gson gson = new Gson();

    private JsonUtil() {
        // Don't
    }

    public static String toJsonString(Object object) {

        if (object != null) return gson.toJson(object);
        return EMPTY_JSON;
    }

    public static  <T>T fromJsonString(String json, Class<T>  clazz) {

        try{
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            Timber.e("Failed to parse object of type %s from JS result: %s", clazz.getName(), json);
        }

        return null;
    }

}