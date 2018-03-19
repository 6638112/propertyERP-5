package com.cnfantasia.server.api.dredge.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.util.DigestUtils;

import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.tenpay.util.MD5Util;

/**
 * 服务订单推送接口
 * @author wenfq  2016年11月4日
 *
 */
public abstract class AbstractServiceOrderPusher {
	/**
	 * 推送新订单
	 * @return 
	 */
	public abstract String pushNewOrder(DredgeBill db);
	
	/**
	 * 订单支付后的信息推送
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public abstract String payOrder(String orderNumber, double price) throws HttpException, IOException;
	
	/**
	 * 获取HttpClient实例
	 * @return
	 */
	abstract public HttpUtil getHttpUtil();
	
	abstract public String getAppKey();
	
	abstract public String getAppSecret();
	
	abstract public String getBaseURL();
	
	public String generateSignature(Map<String, Object> map, String secret){
		 List<String> list = new ArrayList<String>();
		    for (String s : map.keySet()) {
		        list.add(s);
		    }
		    Collections.sort(list);
		    StringBuffer buffer = new StringBuffer();
		    for (String s : list) {
		        buffer = buffer.append(s).append(map.get(s));
		    }
		    String s = buffer.append(secret).toString();
		    System.out.println(s);
		    String s1 = DigestUtils.md5DigestAsHex(s.getBytes()).toUpperCase();
		    return s1;
	}
	
	public boolean verifySignature(Map<String, Object> parameterMap, String accessKeySecret) throws UnsupportedEncodingException{
		 if (!org.springframework.util.StringUtils.hasText(accessKeySecret)) {
	            return false;
	        }
//	        Map<String, String[]> parameterMap = request.getParameterMap();
	        List<String> list = new ArrayList<String>();
	        for (String s : parameterMap.keySet()) {
	            if (!s.equals("sign")) {
	                list.add(s);
	            }
	        }
	        Collections.sort(list);
	        StringBuffer buffer = new StringBuffer();
	        for (String s : list) {
	            buffer.append(s).append(URLDecoder.decode((String) parameterMap.get(s), "utf-8"));
	        }
	        buffer.append(accessKeySecret);
	        String generateSignature = MD5Util.MD5Encode(buffer.toString(), "utf-8").toUpperCase();
	        ///log.debug("generateSignature value is:" + generateSignature);
	        System.out.println("generateSignature value is:" + generateSignature);
	        String receiveSign = (String) parameterMap.get("sign");
	       //log.debug("receiveSign value is :" + receiveSign);
	        System.out.println("receiveSign value is :" + receiveSign);
	        return generateSignature.equals(receiveSign);
	}
}
