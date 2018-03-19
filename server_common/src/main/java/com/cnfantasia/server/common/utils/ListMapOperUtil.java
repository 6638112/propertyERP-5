package com.cnfantasia.server.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ListMapOperUtil {
	public  static void sortListMapOfDateKey(List<HashMap<String,Object>> srcList,String sortKeyName,String dateFormate,boolean asc){
  		if(srcList==null||StringUtils.isEmpty(sortKeyName)){return ;}
  		SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
  		for(int i=0;i<srcList.size();i++){
  			for(int j=i+1;j<srcList.size();j++){
  				Date valueI=null;
  				Date valueJ=null;
  				try {
  					valueI=sdf.parse(srcList.get(i).get(sortKeyName).toString());
				} catch (Exception e) {
					//logger.debug("发现"+sortKeyName+"字段的非法数据"+srcList.get(i).get(sortKeyName)+"，不能转换为日期类型。");
					valueI=null;
				}
				try {
					valueJ=sdf.parse(srcList.get(j).get(sortKeyName).toString());
				} catch (Exception e) {
					//logger.debug("发现"+sortKeyName+"字段的非法数据"+srcList.get(j).get(sortKeyName)+"，不能转换为日期类型。");
					valueJ=null;
				}
				if(asc&&valueI.getTime()>valueJ.getTime()){
  					HashMap<String,Object> tmpMap=srcList.get(i);
  					srcList.set(i, srcList.get(j));
  					srcList.set(j, tmpMap);
  				}
  				
  			}
  		}
  	}
  	/**
	  * 将list的Map集合，按照给定的keyName分组，转换成HashMap对象。
	  * @param list
	  * @param keyName
	  * @return
	  */
 	public static HashMap<String,List<HashMap<String, Object>>> listToMapByKey(List<HashMap<String,Object>> list,Object keyName){
 		HashMap<String,List<HashMap<String, Object>>> resMap=new HashMap<String, List<HashMap<String,Object>>>();
 		Set<String> keys=new HashSet<String>();
 		for(HashMap<String,Object> record:list){
 			if(record.get(keyName)==null){
 				//logger.warn("将list分组时,发现"+keyName+"类型存在非法的null数据。");
 			}else{
 				keys.add(record.get(keyName).toString());
 			}
 		}
 		for(String tmpS:keys){
	    	List<HashMap<String, Object>> tmpList=new LinkedList<HashMap<String, Object>>();
	    	for(HashMap<String,Object> tmpRec:list){
	    		if(tmpS.equals(tmpRec.get(keyName))){
	    			tmpList.add(tmpRec);
	    		}
		    }
	    	//这里是否需要再次排序。。。
	    	//sortListMap(tmpList, sortKeyName);
	    	resMap.put(tmpS, tmpList);
	    }
 		return resMap;
 	}
 	
 	/**
  	 * 将Map集合srcList按照某个sortKeyName的value值进行升序排序，返回排好序的Map集合 
  	 * @param srcList
  	 * @param sortKeyName
  	 * @return
  	 */
  	public  static void sortListMap(List<HashMap<String,Object>> srcList,String sortKeyName,boolean asc){
  		if(srcList==null||StringUtils.isEmpty(sortKeyName)){return ;}
  		for(int i=0;i<srcList.size();i++){
  			for(int j=i+1;j<srcList.size();j++){
  				int valueI=0;
  				int valueJ=0;
  				try {
  					valueI=Integer.parseInt(srcList.get(i).get(sortKeyName).toString());
				} catch (Exception e) {
					//logger.debug("发现"+sortKeyName+"字段的非法数据"+srcList.get(i).get(sortKeyName)+"，不能转换为整数。");
					valueI=0;
				}
				try {
					valueJ=Integer.parseInt(srcList.get(j).get(sortKeyName).toString());
				} catch (Exception e) {
					//logger.debug("发现"+sortKeyName+"字段的非法数据"+srcList.get(j).get(sortKeyName)+"，不能转换为整数。");
					valueJ=0;
				}
				if(asc&&valueI>valueJ){
  					HashMap<String,Object> tmpMap=srcList.get(i);
  					srcList.set(i, srcList.get(j));
  					srcList.set(j, tmpMap);
  				}
  				
  			}
  		}
  	}
  	
  	
}
