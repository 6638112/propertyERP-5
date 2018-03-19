package com.cnfantasia.server.api.pub.utils;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;

/**
 * 公用工具类
 * 可以初始化对象新增、修改、删除等操作
 * */
public class CnfantasiaCommbusiUtil implements ApplicationContextAware {
	
	private static Log logger = LogFactory.getLog(CnfantasiaCommbusiUtil.class);
	
	private static ApplicationContext context;//声明一个静态变量保存
	@Override
	public void setApplicationContext(ApplicationContext contex)
	   throws BeansException {
		CnfantasiaCommbusiUtil.context=contex;
	}
	public static ApplicationContext getContext(){
	  return context;
	}
	public static Object getBeanManager(String beanName){
		return context.getBean(beanName);
	}
	
	/**
	 * 新增对象初始化，会自动将对象的uuid和添加人，修改人设值
	 * @param obj 新增对象
	 * @param tableName 新增对象的表名
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void newStandard(Object obj, String tableName){
		try{
			Method method = null;
			Class clazz = obj.getClass();
			IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
			method = clazz.getMethod("setId",BigInteger.class);
			method.invoke(obj, new Object[] { uuidManager.getNextUuidBigInteger(tableName) });
			BigInteger userId = getCurrentUserId();
			if(userId!= null){
				method = clazz.getMethod("setSys0AddUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0AddTime",String.class);
				method.invoke(obj, new Object[] { CnfantasiaCommbusiUtil.getCurrentTime() });
				
				method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0UpdTime",String.class);
				method.invoke(obj, new Object[] { CnfantasiaCommbusiUtil.getCurrentTime() });
			}	
			
			method = clazz.getMethod("setSys0DelState",Integer.class);
			method.invoke(obj, new Object[] { 0 });
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增多个对象初始化，会自动将对象的uuid和添加人，修改人设值
	 * @param objs 新增对象集合
	 * @param tableName 新增对象的表名
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void newStandardList(List objs, String tableName){
		try{
			IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
			List<BigInteger> objIds = uuidManager.getNextUuidBigInteger(tableName, objs.size());
			Method method = null;
			Object obj = null;
			for (int i = 0; i < objs.size(); i++) {
				obj = objs.get(i);
				Class clazz = obj.getClass();
				method = clazz.getMethod("setId",BigInteger.class);
				method.invoke(obj, new Object[] { objIds.get(i) });
				BigInteger userId = getCurrentUserId();
				if(userId!= null){
					method = clazz.getMethod("setSys0AddUser",BigInteger.class);
					method.invoke(obj, new Object[] { userId });
					
					method = clazz.getMethod("setSys0AddTime",String.class);
					method.invoke(obj, new Object[] { CnfantasiaCommbusiUtil.getCurrentTime() });
					
					method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
					method.invoke(obj, new Object[] { userId });
					
					method = clazz.getMethod("setSys0UpdTime",String.class);
					method.invoke(obj, new Object[] { CnfantasiaCommbusiUtil.getCurrentTime() });
				}	
				
				method = clazz.getMethod("setSys0DelState",Integer.class);
				method.invoke(obj, new Object[] { 0 });
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增多个对象初始化，会自动将对象的uuid赋值
	 * @param objs 新增对象集合
	 * @param tableName 新增对象的表名
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void newStandardListForId(List objs, String tableName){
		try{
			IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
			List<BigInteger> objIds = uuidManager.getNextUuidBigInteger(tableName, objs.size());
			Method method = null;
			Object obj = null;
			for (int i = 0; i < objs.size(); i++) {
				obj = objs.get(i);
				Class clazz = obj.getClass();
				method = clazz.getMethod("setId",BigInteger.class);
				method.invoke(obj, new Object[] { objIds.get(i) });
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改对象初始化，会自动将对象的修改人设值
	 * @param obj 新增对象
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void updateStandard(Object obj){
		try{
			BigInteger userId = getCurrentUserId();
			Class clazz = obj.getClass();
			Method method = null;
			if(userId!= null){
				method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0UpdTime",String.class);
				method.invoke(obj, new Object[] { CnfantasiaCommbusiUtil.getCurrentTime() });
			}	
			method = clazz.getMethod("setSys0DelState",Integer.class);
			method.invoke(obj, new Object[] { 0 });
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除对象初始化，会自动将对象的删除人和删除状态设值
	 * @param obj 新增对象
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void deleteStandard(Object obj){
		try{
			BigInteger userId = getCurrentUserId();
			Class clazz = obj.getClass();
			Method method = null;
			if(userId!= null){
				method = clazz.getMethod("setSys0DelUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0DelTime",String.class);
				method.invoke(obj, new Object[] { CnfantasiaCommbusiUtil.getCurrentTime() });
			}	
			
			method = clazz.getMethod("setSys0DelState",Integer.class);
			method.invoke(obj, new Object[] { 1 });
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前用户的ID
	 * */
	public static BigInteger getCurrentUserId(){
		BigInteger userId = BigInteger.valueOf(1);
		try {
			userId = new BigInteger(UserContext.getOperId());
		} catch (Exception e) {
			logger.error(e);
		}
		return userId;
	}
	
	/**
	 * 获取当前时间
	 * @return String
	 * */
	public static String getCurrentTime(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(getNowTime());
		}catch(Exception e){
			logger.error(e);
			return null;
		}	
	}
	
	/**
	 * 获取当前时间
	 * @param type{Calendar.YEAR,Calendar.MONTH,Calendar.DATE,Calendar.HOUR_OF_DAY}
	 * @param amount增量值
	 * @return String
	 * */
	public static String getNextTime(int type, int amount){
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(getNowTime());
//			if(type==1){
//				cal.add(Calendar.YEAR, amount);
//			}else if(type==2){
//				cal.add(Calendar.MONTH, amount);
//			}else if(type==3){
//				cal.add(Calendar.DATE, amount);
//			}else if(type==4){
//				cal.add(Calendar.HOUR_OF_DAY, amount);
//			}
			cal.add(type, amount);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(cal.getTime());
		}catch(Exception e){
			logger.error(e);
			return null;
		}	
	}
	
//	public static void main(String[] args) {
//		int type = 4;
//		int amount =1;
//		try{
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(new Date());
//			if(type==1){
//				cal.add(Calendar.YEAR, amount);
//			}else if(type==2){
//				cal.add(Calendar.MONTH, amount);
//			}else if(type==3){
//				cal.add(Calendar.DATE, amount);
//			}else if(type==4){
//				cal.add(Calendar.HOUR_OF_DAY, amount);
//			}
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(format.format(cal.getTime())); 
//		}catch(Exception e){
//			logger.error(e);
//		}	
//	}
	
	/**
	 * 转换类型为Biginteger
	 * */
	public static BigInteger convert2BigInteger(Object obj){
		if(obj == null){
			return null;
		}else{
			try{
				return new BigInteger(obj.toString());
			}catch(Exception e){
				logger.error(e);
				return null;
			}			
		}
	}
	
	/**
	 * 发短信
	 * 
	 * @param mobile
	 * @param msg
	 * @return
	 */
	public static boolean sendSMS(String mobile, String msg) {
		try {
			List<String> mobiles = new ArrayList<String>();
			mobiles.add(mobile);
//			String isMessageSend = getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
//			FutureTask<String> task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, isMessageSend));
//			new Thread(task).start();
//			logger.info("发送短信的响应 = " + task.get());
			ShortMsgUtil.sendMessage(mobile, msg);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/*
	 * 拿图片服务器的根路径
	 */
	public static String getFileServiceBasePath() {
		IFileServerService fileServerService = (IFileServerService) getBeanManager("fileServerService");
		return fileServerService.getFileServierUploadBasePath();
	}
	
	/*
	 * 拿图片的完整路径
	 */
	public static String getPicUrl(String basePath, String picName, Date updTime) {
		ISysParamManager sysParamManager = (ISysParamManager) getBeanManager("sysParamManager");
		IFileServerService fileServerService = (IFileServerService) getBeanManager("fileServerService");
		
		String iconBasePath = sysParamManager.getSysParaValue(basePath);
		String[] tempBp = iconBasePath.split(";");
		iconBasePath = tempBp[0];
		
		return fileServerService.getAccessUrl(iconBasePath + picName, updTime);
	}
	
	public static Date getNowTime(){
		return new Date();
	}
	
	public static String getSysParaValue(String key) {
		ISysParamManager sysParamManager = (ISysParamManager) getBeanManager("sysParamManager");
		return sysParamManager.getSysParaValue(key);
	}
	
	public static Integer getSysParaValueInt(String key, Integer defaultValue) {
		ISysParamManager sysParamManager = (ISysParamManager) getBeanManager("sysParamManager");
		try {
			return Integer.valueOf(sysParamManager.getSysParaValue(key).trim());
		} catch(Exception e) {
			return defaultValue;
		}
	}
	
	public static BigInteger getUuid(String tableName) {
		IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
		return uuidManager.getNextUuidBigInteger(tableName);
	}

	public static List<BigInteger> getUuidList(String tableName, int size) {
		IUuidManager uuidManager = (IUuidManager) CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
		return uuidManager.getNextUuidBigInteger(tableName, size);
	}
}
