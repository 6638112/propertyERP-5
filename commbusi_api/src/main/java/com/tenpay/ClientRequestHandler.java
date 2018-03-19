package com.tenpay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientRequestHandler extends PrepayIdRequestHandler {

	public ClientRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public String getXmlBody() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				sb.append("<" + k + ">" + v + "<" + k + ">" + "\r\n");
			}
		}
		return sb.toString();
	}
	@SuppressWarnings("rawtypes")
	public Map<String,Object> getMapBody() {
		Map<String,Object> map = new HashMap<String,Object>();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				map.put(k, v);
			}
		}
		return map;
	}
}
