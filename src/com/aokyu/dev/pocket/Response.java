/*
 * Copyright 2012 Yu AOKI
 */

package com.aokyu.dev.pocket;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.aokyu.dev.pocket.http.HttpParameters;
import com.aokyu.dev.pocket.util.JSONUtils;

public class Response {

    public interface Parameter extends ResponseParameter {

        public static final String STATUS = "status";

    }

    private HttpParameters mParameters = new HttpParameters();

    protected Response(JSONObject jsonObj) throws JSONException {
        if (jsonObj != null) {
            String[] keys = JSONUtils.getKeys(jsonObj);
            int size = keys.length;
            for (int i = 0; i < size; i++) {
                String key = keys[i];
                Object value = jsonObj.get(key);
                put(key, value);
            }
        }
    }

    protected Response(JSONObject jsonObj, String rootKey) throws JSONException {
        JSONObject obj = jsonObj.getJSONObject(rootKey);
        if (obj != null) {
            String[] keys = JSONUtils.getKeys(obj);
            int size = keys.length;
            for (int i = 0; i < size; i++) {
                String key = keys[i];
                Object value = obj.get(key);
                put(key, value);
            }
        }
    }

    /* package */ void put(String key, Object value) {
        mParameters.put(key, value);
    }

    public Set<String> keySet() {
        return mParameters.keySet();
    }

    public int size() {
        return mParameters.size();
    }

    public Object get(String key) {
        return mParameters.get(key);
    }

    public boolean containsKey(String key) {
        return mParameters.containsKey(key);
    }

}