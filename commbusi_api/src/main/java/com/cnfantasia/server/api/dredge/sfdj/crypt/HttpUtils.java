package com.cnfantasia.server.api.dredge.sfdj.crypt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * http请求工具类
 *
 * @author rutine
 * @time Jan 16, 2015 4:17:28 PM
 */
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String getJson(String url, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(url.trim());
        url = urlBuilder.toString();
        if (url.indexOf("?") != -1) {
            if (url.endsWith("&")) {
                urlBuilder.append(buildQueryString(params));
            } else {
                urlBuilder.append("&").append(buildQueryString(params));
            }
        } else {
            urlBuilder.append("?").append(buildQueryString(params));
        }

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(urlBuilder.toString());
        try {
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");

            logger.info("Http response: {}, by invoking {}", json, urlBuilder);
            return json;
        } catch (IOException e) {
            logger.error("1. 请求异常!", e);
        } catch (Exception e) {
            logger.error("2. 请求异常!", e);
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                logger.warn("关闭连接失败!", e);
            }
        }

        return null;
    }

    public static <T> T getObject(String url, Map<String, String> params, Class<T> clazz) {
        String json = getJson(url, params);

        if (clazz.equals(String.class)) {
            return (T) json;
        }
       return JSON.parseObject(json, clazz);
       // return JsonUtils.fromJson(json, clazz);
    }

    public static String deleteJson(String url, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(url.trim());
        url = urlBuilder.toString();
        if (url.indexOf("?") != -1) {
            if (url.endsWith("&")) {
                urlBuilder.append(buildQueryString(params));
            } else {
                urlBuilder.append("&").append(buildQueryString(params));
            }
        } else {
            urlBuilder.append("?").append(buildQueryString(params));
        }

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpDelete httpDelete = new HttpDelete(urlBuilder.toString());
        try {
            HttpResponse httpResponse = closeableHttpClient.execute(httpDelete);
            HttpEntity entity = httpResponse.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");

            return json;
        } catch (IOException e) {
            logger.error("1. 请求异常!", e);
        } catch (Exception e) {
            logger.error("2. 请求异常!", e);
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                logger.warn("关闭连接失败!", e);
            }
        }

        return null;
    }

    public static <T> T deleteObject(String url, Map<String, String> params, Class<T> clazz) {
        String json = deleteJson(url, params);

        if (clazz.equals(String.class)) {
            return (T) json;
        }

//        return JsonUtils.fromJson(json, clazz);
        return JSON.parseObject(json, clazz);
    }

    public static String postJson(String url, Map<String, String> params) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(buildNameValuePairs(params), "UTF-8"));
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");

            return json;
        } catch (IOException e) {
            logger.error("1. 请求异常!", e);
        } catch (Exception e) {
            logger.error("2. 请求异常!", e);
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                logger.warn("关闭连接失败!", e);
            }
        }

        return null;
    }

    public static <T> T postObject(String url, Map<String, String> params, Class<T> clazz) {
        String json = postJson(url, params);

        if (clazz.equals(String.class)) {
            return (T) json;
        }

//        return JsonUtils.fromJson(json, clazz);
        return JSON.parseObject(json, clazz);
    }

    /**
     * 功能说明 : 根据Map参数集合构建名字-值列表
     *
     * @param params Map参数集合
     * @return 返回名字-值列表
     */
    public static List<NameValuePair> buildNameValuePairs(Map<String, String> params) {
        Set<Map.Entry<String, String>> set = params.entrySet();
        Iterator<Map.Entry<String, String>> it = set.iterator();

        List<NameValuePair> list = new ArrayList<NameValuePair>(params.size());
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return list;
    }

    /**
     * 功能说明 : 根据Map参数集合构建查询字符串
     *
     * @param params Map参数集合
     * @return 返回查询字符串
     */
    public static String buildQueryString(Map<String, String> params) {
        if (null == params) {
            return "";
        }

        Set<Map.Entry<String, String>> set = params.entrySet();
        Iterator<Map.Entry<String, String>> it = set.iterator();

        boolean needComma = false;
        StringBuilder queryStruilder = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            queryStruilder.append(needComma ? "&" : "");
            queryStruilder.append(entry.getKey());
            queryStruilder.append("=");
            queryStruilder.append(entry.getValue());
            needComma = true;
        }

        return queryStruilder.toString();
    }
}
