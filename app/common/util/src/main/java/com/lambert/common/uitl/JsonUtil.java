package com.lambert.common.uitl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;

import java.lang.reflect.Type;

/**
 * @author lambert
 * @version $Id: JsonUtil.java, v 0.1 2019年05月09日 10:30 PM lambert Exp $
 */
public class JsonUtil {
    private static JsonConfig JSONCONFIG = new JsonConfig();
    private static Gson GSON;
    static {
        JSONCONFIG.registerJsonValueProcessor(java.util.Date.class, new JsDateJsonValueProcessor());
        GSON = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 从对象获取JSONOjbect
     * 默认Date输出为 yyyy-MM-dd HH:mm:ss
     *
     * @param object
     * @return
     */
    public static JSONObject getJSONObjectFromObject(Object object) {
        return JSONObject.fromObject(object, JSONCONFIG);
    }

    /**
     *
     *
     * @param object
     * @return
     */
    public static JSONArray getJSONArrayFromObject(Object object) {
        return JSONArray.fromObject(object, JSONCONFIG);
    }

    /**
     *
     *
     * @param obj
     * @return
     */
    public static String toGsonString(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     *
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getObjectByGson(String jsonString, Class<T> cls) {
        return GSON.fromJson(jsonString, cls);
    }

    /**
     *
     *
     * @param jsonString
     * @param typeOfT
     * @return
     */
    public static <T> T getObjectByGson(String jsonString, Type typeOfT) {
        return GSON.fromJson(jsonString, typeOfT);
    }
}
