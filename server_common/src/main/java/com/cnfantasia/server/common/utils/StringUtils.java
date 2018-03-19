package com.cnfantasia.server.common.utils;

public class StringUtils {
	public static boolean isStrEmpty(Object obj){ 
		return isEmpty(obj);
	}
	public static boolean isEmpty(Object obj){
		if(obj==null){
			return true;
		}
		if(obj.toString().trim().length()<=0){
			return true;
		}
		return false;
	}
	
	/**
	 * 返回字符串中数字部分
	 * @param srcString
	 * @return
	 */
	public static String getSubDigitString(String srcString){
		if(isEmpty(srcString))
			return null;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < srcString.length(); i++){
			if(Character.isDigit(srcString.charAt(i))){
				sb.append(srcString.charAt(i));
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 返回字符串中字符部分
	 * @param srcString
	 * @return
	 */
	public static String getSubCharString(String srcString){
		if(isEmpty(srcString))
			return null;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < srcString.length(); i++){
			if(!Character.isDigit(srcString.charAt(i))){
				sb.append(srcString.charAt(i));
			}
		}
		
		return sb.toString();
	}

	/**
	 *  隐藏部分业主姓名
	 * @param userName
	 * @return
	 */
	public static String replaceNameToStar(String userName) {
		StringBuilder name = new StringBuilder();
		if(!StringUtils.isEmpty(userName)) {
			if(userName.length() > 2 ) {
				userName = String.valueOf(userName.charAt(0)) + "*" + String.valueOf(userName.charAt(userName.length() - 1));
			} else {
				userName = String.valueOf(userName.charAt(0))+"*";
			}
		} else {
			userName = "";
		}
		return userName;
	}

	/**
	 * 截短字符串前N位
	 * @param sourceStr 原文
	 * @param length 截取长度
	 * @param tailFill 超长部分替代串，null 则不补尾串
	 * @return 截短后的字符
	 */
	public static String subStr(String sourceStr, int length, String tailFill) {
		if (sourceStr == null || sourceStr.trim().length() == 0) {
			return null;
		}
		if (sourceStr.length() <= length) {
			return sourceStr;
		}
		return tailFill == null ? sourceStr.substring(0, length) : sourceStr.substring(0, length) + tailFill;
	}

}
