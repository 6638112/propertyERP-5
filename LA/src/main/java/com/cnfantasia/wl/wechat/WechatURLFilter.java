package com.cnfantasia.wl.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class WechatURLFilter implements Filter {

	//这个Token是给微信开发者接入时填的
	//可以是任意英文字母或数字，长度为3-32字符
	public static String Token = "hynwl12345";

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("WechatURLFilter启动成功!");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//微信服务器将发送GET请求到填写的URL上,这里需要判定是否为GET请求
		boolean isGet = request.getMethod().toLowerCase().equals("get");

		String responseString = "<xml>" + "<ToUserName><![CDATA[%s]]></ToUserName>" + "<FromUserName><![CDATA[%s]]></FromUserName>"
				+ "<CreateTime>%s</CreateTime>" + "<MsgType><![CDATA[%s]]></MsgType>" + "<Content><![CDATA[%s]]></Content>" + "<FuncFlag>0</FuncFlag>"
				+ "</xml>";

		String postString = readStreamParameter(request.getInputStream());

		if (StringUtils.isNotEmpty(postString)) {
			Document document = null;
			try {
				document = DocumentHelper.parseText(postString);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element root = document.getRootElement();
			String fromUsername = root.elementText(WeChatConstant.MSG_FromUserName);
			request.getSession().setAttribute(WeChatConstant.OPENID, fromUsername);
			request.setAttribute(WeChatConstant.OPENID, fromUsername);
		}

		chain.doFilter(request, response);
	}

	//流中读取数据
	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

	@Override
   public void destroy() {
   }

	/**
	 * 验证请求的有效性
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkSignature(HttpServletRequest request) {
		//验证URL真实性
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		List<String> params = new ArrayList<String>();
		params.add(Token);
		params.add(timestamp);
		params.add(nonce);
		//1. 将token、timestamp、nonce三个参数进行字典序排序
		Collections.sort(params, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		//2. 将三个参数字符串拼接成一个字符串进行sha1加密
		String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));

		return temp.equals(signature);
	}
}
