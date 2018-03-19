/**   
 * Filename:    RandomUtils.java   
 * @version:    1.0  
 * Create at:   2014年5月6日 上午8:06:40   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月6日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.common.utils;

/**
 * Filename: RandomUtils.java
 * 
 * @version: 1.0.0 Create at: 2014年5月6日 上午8:06:40 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月6日 shiyl 1.0 1.0 Version
 */
public class RandomUtils {
	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag 是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomUtils.createRandom(false, 4));
		System.out.println(RandomUtils.createRandom(false, 4));
	}
}
