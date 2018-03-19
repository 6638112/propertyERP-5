/**   
 * Filename:    MYSQLEncoder.java   
 * @version:    1.0  
 * Create at:   2014年11月24日 上午3:19:01   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月24日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Filename: MYSQLEncoder.java
 * 
 * @version: 1.0.0 Create at: 2014年11月24日 上午3:19:01 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月24日 shiyl 1.0 1.0 Version
 */
public class MYSQLEncoder {
	private static Map<String, String> referencesMap = new HashMap<String, String>();

	static {
		referencesMap.put("'", "\\'");
		referencesMap.put("\"", "\\\"");
		referencesMap.put("\\", "\\\\");

		referencesMap.put("\n", "\\\n");
		referencesMap.put("\0", "\\\0");
		referencesMap.put("\b", "\\\b");
		referencesMap.put("\r", "\\\r");
		referencesMap.put("\t", "\\\t");
		referencesMap.put("\f", "\\\f");
	}

	// escape sql tag with the source code.
	public static String encode(String source) {
		if (source == null)
			return "";

		StringBuffer sbuffer = new StringBuffer(source.length());

		for (int i = 0; i < source.length(); i++) {
			String c = source.substring(i, i + 1);

			// System.out.println("c=" + c);
			// System.out.println(referencesMap.get(c));

			if (referencesMap.get(c) != null) {
				sbuffer.append(referencesMap.get(c));
			} else {
				sbuffer.append(c);
			}
		}
		return sbuffer.toString();
	}

	public static void main(String[] args) {
		String tt = encode("They'are \"ok\". \\a");
		System.out.print(tt);
	}
}
