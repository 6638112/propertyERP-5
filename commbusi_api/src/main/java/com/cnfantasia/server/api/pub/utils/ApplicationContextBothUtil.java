package com.cnfantasia.server.api.pub.utils;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.encrypt.service.IEncryptService;
import com.cnfantasia.server.commbusi.microblogQueue.serviceUn.IGbLeastDiscountService;
import com.cnfantasia.server.commbusi.microblogQueue.serviceUn.IMicroblogQueueService;
import com.cnfantasia.server.commbusi.microblogQueue.task.IMicroblogQueueFactory;
import com.cnfantasia.server.commbusi.prizeActivity.service.IPrizeActivityService;
import com.cnfantasia.server.commbusi.prizeActivity.service.IPrizeOptionService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * 公用工具类
 * 可以初始化对象新增、修改、删除等操作
 * */
public class ApplicationContextBothUtil implements ApplicationContextAware {
	private static Log logger = LogFactory.getLog(ApplicationContextBothUtil.class);
	private static ApplicationContext context;//声明一个静态变量保存
	@Override
	public void setApplicationContext(ApplicationContext contex)
	   throws BeansException {
		ApplicationContextBothUtil.context=contex;
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
	public static void newStandard(Object obj, String tableName,BigInteger userId){
		try{
			Method method = null;
			Class clazz = obj.getClass();
			IUuidManager uuidManager = getUuidManager();
			method = clazz.getMethod("setId",BigInteger.class);
			method.invoke(obj, new Object[] { uuidManager.getNextUuidBigInteger(tableName) });
//			BigInteger userId = getCurrentUserId();
			if(userId!= null){
				method = clazz.getMethod("setSys0AddUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0AddTime",String.class);
				method.invoke(obj, new Object[] { getCurrentTime() });
				
				method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0UpdTime",String.class);
				method.invoke(obj, new Object[] { getCurrentTime() });
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
	public static void newStandardList(List objs, String tableName,BigInteger userId){
		try{
			IUuidManager uuidManager = getUuidManager();
			List<BigInteger> objIds = uuidManager.getNextUuidBigInteger(tableName, objs.size());
			Method method = null;
			Object obj = null;
			for (int i = 0; i < objs.size(); i++) {
				obj = objs.get(i);
				Class clazz = obj.getClass();
				method = clazz.getMethod("setId",BigInteger.class);
				method.invoke(obj, new Object[] { objIds.get(i) });
//				BigInteger userId = getCurrentUserId();
				if(userId!= null){
					method = clazz.getMethod("setSys0AddUser",BigInteger.class);
					method.invoke(obj, new Object[] { userId });
					
					method = clazz.getMethod("setSys0AddTime",String.class);
					method.invoke(obj, new Object[] { getCurrentTime() });
					
					method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
					method.invoke(obj, new Object[] { userId });
					
					method = clazz.getMethod("setSys0UpdTime",String.class);
					method.invoke(obj, new Object[] { getCurrentTime() });
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
	 * 修改对象初始化，会自动将对象的修改人设值
	 * @param obj 新增对象
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void updateStandard(Object obj,BigInteger userId){
		try{
//			BigInteger userId = getCurrentUserId();
			Class clazz = obj.getClass();
			Method method = null;
			if(userId!= null){
				method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
				method.invoke(obj, new Object[] { userId });
				
				method = clazz.getMethod("setSys0UpdTime",String.class);
				method.invoke(obj, new Object[] { getCurrentTime() });
			}	
			method = clazz.getMethod("setSys0DelState",Integer.class);
			method.invoke(obj, new Object[] { 0 });
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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
	
	public static Date getNowTime(){
		IDualDao dualDao = (IDualDao)getDualDao();
		String nowTime = dualDao.getNowTime();
		try {
			return DateUtil.formatSecond.get().parse(nowTime);
		} catch (ParseException e) {
			throw new BusinessRuntimeException(e);
		}
	}
	
//	/**
//	 * 获取当前用户的ID
//	 * */
//	public static BigInteger getCurrentUserId(){
//		BigInteger userId = BigInteger.valueOf(1);
//		try {
//			userId = new BigInteger(UserContext.getOperId());
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		return userId;
//	}
	
	public static IDualDao getDualDao(){//TODO ..
		return context.getBean("dualDao", IDualDao.class);
	}
//	public static MessageSourceUtil getMessageSourceUtil(){//TODO ..
//		return context.getBean("messageSourceUtil", MessageSourceUtil.class);
//	}
	
	public static IUuidManager getUuidManager(){
		return context.getBean("uuidManager", IUuidManager.class);
	}
	
	public static IMicroblogQueueFactory getMicroblogQueueFactory(){
		return context.getBean("microblogQueueFactory", IMicroblogQueueFactory.class);
	}
	
	public static IMicroblogQueueService getMicroblogQueueService(){
		return context.getBean("microblogQueueService", IMicroblogQueueService.class);
	}
	public static IGbLeastDiscountService getGbLeastDiscountService(){
		return context.getBean("gbLeastDiscountService", IGbLeastDiscountService.class);
	}
	
	public static ISysParamManager getSysParamManager(){
		return context.getBean("sysParamManager", ISysParamManager.class);
	}
	
	public static IFileServerService getFileServerService(){
		return context.getBean("fileServerService", IFileServerService.class);
	}
	
	public static String getAbsolutePath(String currValue,String sysParaKey,String lastUpdTime){
		IFileServerService fss = getFileServerService();
		ISysParamManager sysParamManager = getSysParamManager();
		String lastPath = fss.getAccessUrl(sysParamManager.getSysParaValue(sysParaKey)+currValue, lastUpdTime);
		return lastPath;
	}
	
	public static IPrizeActivityService getPrizeActivityService(){
		return context.getBean("prizeActivityService", IPrizeActivityService.class);
	}
	
	public static IPrizeOptionService getPrizeOptionService(){
		return context.getBean("prizeOptionService", IPrizeOptionService.class);
	}
	
	public static IEncryptService getEncryptService(){
		return context.getBean("encryptService", IEncryptService.class);
	}
}
