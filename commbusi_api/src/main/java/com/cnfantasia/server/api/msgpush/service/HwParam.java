package com.cnfantasia.server.api.msgpush.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public final class HwParam {

    public static String HOST_ACCESS_TOKEN = "https://login.vmall.com/oauth2/token";
    public static String grant_type = "client_credentials";
    public static String client_id = "10193187";
    public static String client_secret = "gmngyjvst29i8u25oo9p6gu4ifh6c2yy";
    public static String KEY_REDIS_HW_ACCESS_TOKEN = "key_redis_hw_access_token";
    public static String HOST_MSG = "https://api.vmall.com/rest.php";

    public static Map<String, Object> MAP_ACCESSTOKEN = new HashMap<String, Object>() {
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

    // 2013-06-03T17:30:08+08:00
    public static ThreadLocal<DateFormat> DATAFORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private HwParam() {
    }
}
