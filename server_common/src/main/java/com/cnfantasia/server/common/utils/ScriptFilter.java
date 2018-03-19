package com.cnfantasia.server.common.utils;

/**
 * @author yewj
 */
public class ScriptFilter {

	public static String escape(String html) {

		if (html == null) return null;

		String result = html.replace("<", "");
		result = result.replace(">", "");
		result = result.replace("&", "");
		result = result.replace("\"", "");
		result = result.replace("'", "");
		return result;
	}

	public static void main(String[] args) {
		System.out.println(escape("<script>window.open('http://www.youshang.com');</script>"));
	}

}
