package com.cnfantasia.server.api.dredge.sfdj.crypt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数解析类，加密解密组装，所有字节码转换统一采用UTF-8编码
 * 
 * 加密流程： 1、对于除去appkey的参数进行签名，签名的采用排序的参数和值的字符串，比如k1=v1k2=v2
 * 2、将签名加入到参数map中，key为sign 3、将map转换成字符串，采用AES加密 4、对AES加密后的数据采用base64编码
 * 5、重新组装参数，包括appkey和param 6、对参数进行urlencode编码
 * 
 * 解密流程： 1、获取param参数数据，通过base64解码 2、对解码后的数据通过AES解密 3、得到签名数据
 * 4、去掉sign参数，对剩余参数进行签名，和加密流程1一致 5、对比签名是否一致 6、重新组装参数，加入appkey
 * 
 * eg: 加密前：k1=v1&k2=v2&appkey=cp1
 * 加密后(经过urlencode)：param=VH9mvgqekFG4LeOiGdkxbbnHphapSTHBh1HJ5NdwD1qu
 * %2BzRmZ%2BRe2bCvpnaGL%2BtUCsxuvTbl%2F%2Fjx2QLSwl4URg%3D%3D&appkey=cp1
 * 
 * @author fengli
 * @date 2015年5月30日 下午4:54:20
 */
@SuppressWarnings("unchecked")
public class OpenApiParameterParser {

    private static final Logger logger = LoggerFactory
            .getLogger(OpenApiParameterParser.class);
    
    private static final String encoding = "UTF-8";
    private static final String PARAMS_KEY = "param";
    private static final String SIGN_KEY = "sign";
    private static final String APPKEY_KEY = "app_key";

    /**
     * 参数编码，对除了appkey之外的参数进行签名、加密、base64编码
     * 
     * @param params
     * @param secretKey
     * @return
     */
    public static Map<String, Object> encodeParametes(Map params,
            String secretKey) {
        try {
            String appkey = getParamString(params, APPKEY_KEY);
            params.remove(APPKEY_KEY);
            // 对剩余参数进行签名
            String sign = sign(params, secretKey);
            System.out.println("sign : " + sign);
            // 加入sign参数
            params.put(SIGN_KEY, sign);
            // AES加密，secretkey是32字节十六进制串，要转换成16字节的byte数组，作为128位密钥
            byte[] aesBytes = EncrpytionUtil.encryptByAES(
                    paramsToString(params, encoding).getBytes(encoding),
                    EncrpytionUtil.hexStringToBytes(secretKey));
            // base64编码
            Base64 base64 = new Base64();
            String base64Str = base64.encodeToString(aesBytes);
            System.out.println("base64 : " + base64Str);
            Map<String, Object> result = new HashMap<String, Object>();
            if (appkey != null) {
                result.put(APPKEY_KEY, appkey);
            }
            result.put(PARAMS_KEY, base64Str);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数解码，得到用户真正传输的参数
     * 
     * @param params
     * @param secretKey
     * @return
     */
    public static Map<String, Object> decodeParameters(Map params,
            String secretKey) {
        try {
            logger.info("encode params : " + getParamString(params, PARAMS_KEY));
            // base64解码，得到aes加密或的字节码
            Base64 base64 = new Base64();
            byte[] aesBytes = base64.decode(getParamString(params, PARAMS_KEY)
                    .getBytes(encoding));
            if (aesBytes == null) {
                logger.info("base64 decode error!");
            } 
            // 对param参数进行AES解密，得到参数字符串，secretkey是32字节十六进制串，要转换成16字节的byte数组，作为128位密钥
            byte[] buf = EncrpytionUtil.decryptByAES(aesBytes,
                    EncrpytionUtil.hexStringToBytes(secretKey));
            if (buf == null) {
                logger.info("aes decode error!");
            } else {
                logger.info("decode params : " + new String(buf, encoding));
            }
            // 字符串参数转换成map
            Map<String, Object> parameters = paramsToMap(new String(
                    buf, encoding));
            String sign = (String) parameters.get(SIGN_KEY);
            parameters.remove(SIGN_KEY);
            // 重新签名
            String newSign = sign(parameters, secretKey);
            logger.info("new sign : " + newSign + "[old=" + sign + "]");
            if (newSign != null && newSign.equals(sign)) {// 签名通过，加入appkey参数即是完整参数
                parameters.put(APPKEY_KEY, params.get(APPKEY_KEY));
                return parameters;
            } else {
                logger.info("签名不一致！");
                // 签名不一致，暂时返回null
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * map参数格式化字符串，对GET请求可以通过这个方法组织url
     * 
     * @param params
     * @return
     */
    public static String paramsToString(Map<String, Object> params) {
        if (params == null || params.size() == 0) {
            return "";
        }
        Set<String> keys = params.keySet();
        StringBuilder sb = new StringBuilder();
        for (String k : keys) {
            sb.append(k + "=" + getParamString(params, k) + "&");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
    
    public static String paramsToString(Map<String, Object> params, String encoding) throws UnsupportedEncodingException {
        if (params == null || params.size() == 0) {
            return "";
        }
        Set<String> keys = params.keySet();
        StringBuilder sb = new StringBuilder();
        for (String k : keys) {
            sb.append(k + "=" + URLEncoder.encode(getParamString(params, k), encoding) + "&");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
    

    /**
     * url参数转换成map
     * 
     * @param params
     * @return
     */
    public static Map<String, Object> paramsToMap(String params) {
        if (params == null || params.length() == 0) {
            return null;
        }
        Map<String, Object> mapRequest = new HashMap<String, Object>();
        String[] arrSplit = null;
        String strUrlParam = params;
        if (strUrlParam == null) {
            return mapRequest;
        }
        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (arrSplitEqual.length > 1) {
                try {
                    // 正确解析
                    mapRequest.put(arrSplitEqual[0], URLDecoder.decode(arrSplitEqual[1], encoding));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 获取参数签名
     * 
     * @param data
     * @param secretKey
     *            CP app的密钥，由葡萄生活统一分配
     * @return
     */
    public static String sign(Map<String, Object> params, String secretKey) {
        List<String> sortedParams = sortParameters(params);
        String sign = EncrpytionUtil.hmacmd5(concat(sortedParams).toLowerCase(), secretKey,
                encoding);
        return sign;
    }

    /**
     * 对参数列表进行排序，并拼接key-value，默认从大到小排序，排序前统一转换成小写
     * 
     * @param params
     * @return
     */
    private static List<String> sortParameters(Map<String, Object> params) {
        Set<String> keys = params.keySet();
        List<String> paramsBuf = new ArrayList<String>();
        for (String k : keys) {
            paramsBuf.add((k + "=" + getParamString(params, k)));
        }
        // 对参数排序
        List<String> sortParams = sort(paramsBuf);
        return sortParams;
    }

    public static String getParamString(Map params, String key) {
        String buf = "";
        if (params.get(key) instanceof String[]) {
            buf = ((String[]) params.get(key))[0];
        } else {
            buf = (String) params.get(key);
        }
        return buf;
    }

    /**
     * 字符串列表从大到小排序
     * 
     * @param data
     * @return
     */
    private static List<String> sort(List<String> data) {
        Collections.sort(data, new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);
            }
        });
        return data;
    }

    /**
     * 连接字符串列表
     * 
     * @param data
     * @return
     */
    private static String concat(List<String> data) {
        if (data == null || data.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String item : data) {
            sb.append(item);
        }
        return sb.toString();
    }
    
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = URL;
        if (strUrlParam == null) {
            return mapRequest;
        }
        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String appkey = "ayibang";
//        String secret = "a47673a35f9747fbb25c3739f165085f";
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put(APPKEY_KEY, new String[] { appkey });
//        params.put("code", new String[] { "sdfsdfsdfsd" });
//        params.put("open_token", new String[] { "pt1411639133286-10057" });
//        params.put("ut", new String[] { "2015-06-02 15:00:00" });
//        params.put("ts", new String[] { String.valueOf(new Date().getTime()) });
//        Map<String, Object> encodeMap = encodeParametes(params, secret);
//
//        System.out.println("encode : " + paramsToString(encodeMap));
//        System.out.println("url encode : "
//                + URLEncoder.encode(paramsToString(encodeMap), "utf-8"));
//
//        Map<String, Object> decodeMap = decodeParameters(encodeMap, secret);
//
//        System.out.println("decode : " + paramsToString(params));
//
//        Set<String> keys = decodeMap.keySet();
//        for (String k : keys) {
//            System.out.println(k + " = " + decodeMap.get(k));
//        }
        
        String secret = "47bf7226cada89a2764cb1d565b64a23";
        
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("uid","pt1411477761956-10034");
        params.put("app_key", "753a55bc26583c528b964d45d7305b28bafe7c75");
        params.put("timestamp",String.valueOf(System.currentTimeMillis()));
        params.put("cp_order_no", "1488");
        params.put("price","1");
        params.put("ctime",String.valueOf(new Date().getTime()));
        params.put("expand_status","1");
        String detail_url = "http://u12.oboard.net/mdj/qqOrder/orderDetail/?memberId=557ff1219d89d41c18a4a52c&orderId=55823c12e59103a4b6457640&queryFlag=2";
        params.put("detail_url", detail_url);
        
        String body = "新娘妆1";
        String subject = "新娘妆1";
        params.put("body",body);
        params.put("subject",subject);

        Map newParams = OpenApiParameterParser.encodeParametes(params, secret);
        
        Map decode = decodeParameters(newParams, secret);
        Set<String> keys = decode.keySet();
        for (String k : keys) {
            System.out.println(k + "=" + decode.get(k));
        }
        
        System.out.println("");
        
        String p = "sign=502394ecf86d053311a7c036caedaa78&timestamp=1434610011888&uid=pt1411477761956-10034"
                + "&body=新娘妆1&detail_url=http://u12.oboard.net/mdj/qqOrder/orderDetail/?memberId=557ff1219d89d41c18a4a52c&orderId=55823c12e59103a4b6457640"
                + "&queryFlag=2&cp_order_no=1488&price=1&subject=新娘妆1&expand_status=1&ctime=1434610011888";
        
        Map map = paramsToMap(p);
        keys = map.keySet();
        for (String k : keys) {
            System.out.println(k + "=" + map.get(k));
        }
        String sign = sign(map, "47bf7226cada89a2764cb1d565b64a23");
        System.out.println(sign);
    }
}
