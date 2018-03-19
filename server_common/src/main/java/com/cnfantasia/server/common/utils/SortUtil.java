package com.cnfantasia.server.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SortUtil {
  /** 日志对象 */
  private static Log logger = LogFactory.getLog(SortUtil.class);
	public static List<String> sortList(List<String> srList,boolean asc, String eCodeType){
		Object[] keys = (Object[])srList.toArray();
		int length=keys.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				String valueI = "";
				String valueJ = "";
				try {
					valueI = keys[i].toString();
				} catch (Exception e) {
					logger.info("发现数据" + keys[i] + "异常。");
				}
				try {
					valueJ = keys[j].toString();
				} catch (Exception e) {
					logger.info("发现数据" + keys[j] + "异常。");
				}
				// logger.info(valueI);
				// logger.info(valueJ);
				if (asc & SortUtil.compare(valueI, valueJ, eCodeType) > 0) {
					keys[i]=valueJ;
					keys[j]=valueI;
				}
			}//for (int j = i + 1; j < length; j++) {
		}//for (int i = 0; i < length; i++) {
		List<String> resList=new LinkedList<String>();
		for(int i=0;i<keys.length;i++){
			resList.add(keys[i].toString());
		}
		return resList;
	}

	/**
	 * 将srcMap根据key的字符串值排序，不会影响srcMap的值，而是返回新的组装好的map
	 * 
	 * @param srcMap
	 * @param asc
	 * @param eCodeType
	 */
	@SuppressWarnings("rawtypes")
  public static LinkedHashMap sortMap(Map srcMap,boolean asc, String eCodeType) {
		if (srcMap == null || srcMap.size() == 0) {return null;}
		Object[] keys = (Object[])srcMap.keySet().toArray();
		int length=keys.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				String valueI = "";
				String valueJ = "";
				try {
					valueI = keys[i].toString();
				} catch (Exception e) {
					logger.info("发现数据" + keys[i] + "异常。");
				}
				try {
					valueJ = keys[j].toString();
				} catch (Exception e) {
					logger.info("发现数据" + keys[j] + "异常。");
				}
				// logger.info(valueI);
				// logger.info(valueJ);
				if (asc & SortUtil.compare(valueI, valueJ, eCodeType) > 0) {
					keys[i]=valueJ;
					keys[j]=valueI;
				}
			}//for (int j = i + 1; j < length; j++) {
		}//for (int i = 0; i < length; i++) {
		LinkedHashMap<String, Object> resMap=new LinkedHashMap<String, Object>();
		for(int i=0;i<keys.length;i++){
			resMap.put(keys[i].toString(), srcMap.get(keys[i]));
		}
		return resMap;
	}

	/**
	 * 将Map集合srcList按照某个sortKeyName的value值进行升序排序，返回排好序的Map集合
	 * 
	 * @param srcList
	 * @param sortKeyName
	 * @return
	 */
	public static void sortListMap(List<Map<Integer, Object>> srcList, Integer sortKeyName, boolean asc, String eCodeType) {
		if (srcList == null || sortKeyName == null) {
			return;
		}
		for (int i = 0; i < srcList.size(); i++) {
			for (int j = i + 1; j < srcList.size(); j++) {
				String valueI = "";
				String valueJ = "";
				try {
					valueI = srcList.get(i).get(sortKeyName).toString();
				} catch (Exception e) {
					logger.info("发现" + sortKeyName + "字段的非法数据" + srcList.get(i).get(sortKeyName) + "，不能转换为整数。");
				}
				try {
					valueJ = srcList.get(j).get(sortKeyName).toString();
				} catch (Exception e) {
					logger.info("发现" + sortKeyName + "字段的非法数据" + srcList.get(j).get(sortKeyName) + "，不能转换为整数。");
				}
				// logger.info(valueI);
				// logger.info(valueJ);
				if (asc & SortUtil.compare(valueI, valueJ, eCodeType) > 0) {
					Map<Integer, Object> tmpMap = srcList.get(i);
					srcList.set(i, srcList.get(j));
					srcList.set(j, tmpMap);
				}

			}
		}
	}

	public static int compare(String o1, String o2, String eCodeType) {
		try {
			byte[] buf1 = ((String) o1).getBytes(eCodeType);
			byte[] buf2 = ((String) o2).getBytes(eCodeType);
			int size = Math.min(buf1.length, buf2.length);
			for (int i = 0; i < size; i++) {
				if (buf1[i] != buf2[i]) {
					return buf1[i] - buf2[i];
				}
			}
			return buf1.length - buf2.length;
		} catch (UnsupportedEncodingException ex) {
			return 0;
		}
	}

	public static int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}

}
