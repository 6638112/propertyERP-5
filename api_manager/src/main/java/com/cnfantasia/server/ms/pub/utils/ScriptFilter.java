package com.cnfantasia.server.ms.pub.utils;

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
		return result.trim();
	}

}
