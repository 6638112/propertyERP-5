package com.cnfantasia.server.common.messageSource;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.FocRuntimeException;
import com.cnfantasia.server.common.utils.SortUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * 描述:消息资源处理工具类
 * 
 * @version 1.00
 * 
 */

public class MessageSourceUtil {
	public static final Locale DEFAULT_LOCAL=Locale.SIMPLIFIED_CHINESE;
	/** 消息资源对象 */
	private static MessageSource messageSource;

	public static void setMessageSource(MessageSource messageSource) {
		MessageSourceUtil.messageSource = messageSource;
	}
	
	/** 日志对象 */
	private static Log logger = LogFactory.getLog(MessageSourceUtil.class);
	private static final String dictEncodeType="UTF-8";
	/**
	 * 获取资源文件信息
	 * @param msgkey 
	 * @param paramArrayOfObject 参数
	 * @param defaultmsg 默认信息,没有则传值为null
	 * @return
	 */
	public static String getMessage(String msgkey,Object[] paramArrayOfObject, String defaultmsg) {
	  if (messageSource == null) {
      throw new FocRuntimeException(MessageSourceUtil.class+"The messageSource is null..");
    }
    if (StringUtils.isStrEmpty(msgkey)) {
      logger.debug("----the msgkey is empty");
      return "";
    }
    String strmsg = messageSource.getMessage(msgkey, paramArrayOfObject, defaultmsg, DEFAULT_LOCAL);
    return strmsg;
	}
	/**
	 * 获取资源文件信息
	 * 
	 * @param msgkey the key
	 * @param defaultmsg 默认消息
	 * @return
	 */
	public static String getMessage(String msgkey, String defaultmsg) {
	  return getMessage(msgkey, new Object[1], defaultmsg);
	}
	
	/**
	 * 获取资源文件信息
	 * 
	 * @param msgkey the key
	 * @return
	 */
	public static String getMessage(String msgkey) {
		String defaultmsg = null;
		return getMessage(msgkey, defaultmsg);
	}
	public static String getMessage(String msgkey,Object[] paramArrayOfObject) {
		return getMessage(msgkey, paramArrayOfObject,null);
	}
	 /**
   * 加载数据字典信息
   * @param srcListMap
   * @param tableName
   */
  @SuppressWarnings("rawtypes")
	public static void insertDictInfo(List srcListMap,String tableName,String[] columnKeys){
    for(Object srcMap:srcListMap){
      insertDictInfo(srcMap, tableName,columnKeys);
    }
  }
	/**
	 * 加载数据字典信息
	 * @param srcListMap
	 * @param tableName
	 */
	@SuppressWarnings("rawtypes")
	public static void insertDictInfo(List srcListMap,String tableName){
	  insertDictInfo(srcListMap, tableName, null);
  }
	public static void insertDictInfo(HashMap<String,Object> srcMap,String tableName){
	  insertDictInfo(srcMap, tableName,null);
	}
	/**
	 * 加载数据字典信息
	 * @param srcMap 原始数据Map 该信息必须只来源于一张表
	 * @param tableName 表名
	 * @param columnKeys 表需要加载数据字典的列名，若为空，则遍历所有额字段
	 */
  @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void insertDictInfo(Object srcMapTmp,String tableName,String[] columnKeys){
    if(!(srcMapTmp instanceof Map)){
      logger.error(srcMapTmp+"is not type of map");
      return;
    }
    Map srcMap=(Map)srcMapTmp;
    if(srcMap==null||srcMap.size()<=0) return;
    String[] currKeys;
    if(columnKeys!=null&&columnKeys.length>0){
      currKeys=columnKeys;
    }else{
      currKeys=new String[srcMap.size()];
      srcMap.keySet().toArray(currKeys);
    }
    for(String columnName:currKeys){//DICT.userTable.gender.M=男
      StringBuffer sourceKey=new StringBuffer();
      sourceKey.append(CommConstants.DICT_SRC_KEY_PRE);
      sourceKey.append(".");
      sourceKey.append(tableName);
      sourceKey.append(".");
      sourceKey.append(columnName);
//      sourceKey.append(".");
//      sourceKey.append(srcMap.get(columnName).toString());
      if(getMessage(sourceKey.toString())!=null){
        Map<String,Object> dictMap=getDictMap(sourceKey.toString());
        if(srcMap.get(columnName)!=null&&dictMap!=null){
          Object dictValue=dictMap.get(srcMap.get(columnName).toString().trim());
          if(dictValue!=null){
              srcMap.put(columnName+CommConstants.DICT_RES_KEY_TAIL, dictValue);
            }else{
              throw new FocRuntimeException("The dict key is not defined："+sourceKey.toString()+"--"+srcMap.get(columnName));
             }
          }
        }
    }
  }
  /**
   * 根据数据字典key得到对应值的map
   * @param dictKey
   * @return
   */
  @SuppressWarnings("unchecked")
  public static Map<String,Object> getDictMap(String dictKey){
    Map<String,Object> resMap=null;
    String json="{"+getMessage(dictKey)+"}";
    try {
      JSONObject dictMap=null;
      dictMap=JSON.parseObject(json);
      //dictMap根据Key值进行升序排序
      resMap=SortUtil.sortMap(dictMap, true, dictEncodeType);
    } catch (Exception e) {
      logger.debug("The value for dick key parse error:"+dictKey+"-->"+json);
    }
    return resMap;
  }
  
}
