/**   
 * Filename:    PinyinUtil.java   
 * @version:    1.0  
 * Create at:   2014年5月29日 上午7:19:15   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月29日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Filename: PinyinUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年5月29日 上午7:19:15 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月29日 shiyl 1.0 1.0 Version
 */
public class PinyinUtil {
//	public static String hanyuToPinyinSimple(String hanyu) throws BadHanyuPinyinOutputFormatCombination{
//		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		String pinyin = PinyinHelper.toHanyuPinyinString(hanyu, defaultFormat, "");
//		return pinyin;
//	}
	public static String hanyuToPinyinFirst(String hanyu) throws BadHanyuPinyinOutputFormatCombination{
		StringBuffer pinyinNameSbf = new StringBuffer();
		char[] nameChar = hanyu.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				String[] strArry = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
				if(strArry!=null&&strArry.length>0){
					String sTmp = strArry[0];
					if(sTmp!=null&&sTmp.length()>0){
						pinyinNameSbf.append(sTmp.substring(0, 1));
					}
				}
			}else{
				pinyinNameSbf.append(nameChar[i]);
			}
		}
		return pinyinNameSbf.toString();
	}
	public static String hanyuToPinyinSimple(String hanyu) throws BadHanyuPinyinOutputFormatCombination{
		return hanyuToPinyinSimple(hanyu, false);
	}
	
	public static String hanyuToPinyinSimple(String hanyu,boolean isAll) throws BadHanyuPinyinOutputFormatCombination{
		StringBuffer pinyinNameSbf = new StringBuffer();
		char[] nameChar = hanyu.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				String[] strArr = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
				if(strArr!=null&&strArr.length>0){
					pinyinNameSbf.append(strArr[0]);
				}
			}else{
				if(isAll){pinyinNameSbf.append(nameChar[i]);}
			}
		}
		return pinyinNameSbf.toString();
	}
	
//	/**
//	 * 汉字转拼音的方法
//	 * @param name 汉字
//	 * @return 拼音
//	 * @throws BadHanyuPinyinOutputFormatCombination 
//	 */
//	private static String hanyuToPinyin(String name) throws BadHanyuPinyinOutputFormatCombination {
//		String pinyinName = "";
//		
//		char[] nameChar = name.toCharArray();
//		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		for (int i = 0; i < nameChar.length; i++) {
//			if (nameChar[i] > 128) {
//				try {
//					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
////		System.out.println(PinyinHelper.toHanyuPinyinString("大家;好", defaultFormat, ";"));
////		System.out.println(JSON.toJSONString(PinyinHelper.toHanyuPinyinStringArray('你', defaultFormat)));
//		return pinyinName;
//	}

	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		System.out.println(hanyuToPinyinSimple("sw文进"));
		System.out.println(hanyuToPinyinSimple("sw文进",true));
//		System.out.println(hanyuToPinyinSimple("肯德基（观澜餐厅）"));
//		System.out.println(hanyuToPinyinFirst("瑞福城02(燕歌园A003区) "));
	}
}
