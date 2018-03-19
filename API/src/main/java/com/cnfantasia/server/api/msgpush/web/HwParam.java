package com.cnfantasia.server.api.msgpush.web;

import java.util.HashMap;
import java.util.Map;

public class HwParam {

    public static String HOST_ACCESS_TOKEN = "https://login.vmall.com/oauth2/token";
    public static String grant_type = "client_credentials";
    public static String client_id = "10193187";
    public static String client_secret = "gmngyjvst29i8u25oo9p6gu4ifh6c2yy";
    
    public static String KEY_REDIS_HW_TOKEN = "key_redis_hw_token";

    public static Map<String, Object> ACCESSTOKENMAP = new HashMap<String, Object>() {
        /**
        * 
        */
        private static final long serialVersionUID = 979383400060672239L;

        {
            put("grant_type", grant_type);
            put("client_id", client_id);
            put("client_secret", client_secret);
        }
    };
}
