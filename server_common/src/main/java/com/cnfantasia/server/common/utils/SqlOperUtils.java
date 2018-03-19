package com.cnfantasia.server.common.utils;

import java.util.Set;


public class SqlOperUtils {
	/**
	 * 将第1个?替换为处理后的字符串
	 * @param sqlModel
	 * @param list
	 * @return
	 */
	/*public static String sqlMaker(String sqlModel,List<String> list){
		String tmpStr=list.toString();
		tmpStr=tmpStr.substring(1, tmpStr.length()-1);
		String sql=new String(sqlModel);
		sql=sql.replaceFirst("\\?", tmpStr);
		return sql;
	}*/
	/**
	 * 将第index个?替换为处理后的字符串(index从1开始)
	 * @param sqlModel sql模型
	 * @param setTmp 用于配置sql字符串
	 * @param index 替换的?位置
	 * @return 若查找失败则返回原sql，否则返回替换好的sql.
	 */
	public static String sqlMaker(String sqlModel,Set<String> setTmp,int index){
		//定义拆分的字符
		String splitStr="?";
		//将list转为字符串。
		StringBuffer listSbf=new StringBuffer("'',");
		for(String s0:setTmp){
			if(!StringUtils.isEmpty(s0)){
				listSbf.append("'"+s0+"',");
			}
		}
		listSbf=listSbf.deleteCharAt(listSbf.lastIndexOf(","));
		//获取sql模型
		StringBuffer sbf=new StringBuffer(sqlModel);
		//查找出第index个匹配?的位置
		int count=0;
		int startIndex=-1;
		while(count<index){
			startIndex=sbf.indexOf(splitStr,startIndex+1);
			if(startIndex==-1){break;}
			count++;
		}
		//替换此处的?为指定的字符串。
		if(startIndex!=-1){
			sbf=sbf.replace(startIndex,startIndex+1,listSbf.toString());
		}
		return sbf.toString();
	}
}
