package com.cnfantasia.server.api.ebuy.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToMap {
	//key值不定的json
	public static Map jsonToMap(String param) throws JSONException{
        JSONObject jsonObject = new JSONObject(param);
        Map<String,Object> result = new HashMap<String,Object>();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;
	}
}
