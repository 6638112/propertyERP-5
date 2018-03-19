package com.cnfantasia.server.api.pub.utils;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.business.commonBusiness.util.SendSmsRunnable;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * 公用工具类
 * 可以初始化对象新增、修改、删除等操作
 * */
public class EbuyMerchantUtil {
	
	private static Log logger = LogFactory.getLog(EbuyMerchantUtil.class);
	
	
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
			if(tableName != null) {
				IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
				method = clazz.getMethod("setId",BigInteger.class);
				method.invoke(obj, new Object[] { uuidManager.getNextUuidBigInteger(tableName) });
			}
			
			method = clazz.getMethod("setSys0AddTime",String.class);
			method.invoke(obj, new Object[] { EbuyMerchantUtil.getCurrentTime() });
			
			method = clazz.getMethod("setSys0UpdTime",String.class);
			method.invoke(obj, new Object[] { EbuyMerchantUtil.getCurrentTime() });
			
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
				
				method = clazz.getMethod("setSys0AddTime",String.class);
				method.invoke(obj, new Object[] { EbuyMerchantUtil.getCurrentTime() });
				
				method = clazz.getMethod("setSys0UpdTime",String.class);
				method.invoke(obj, new Object[] { EbuyMerchantUtil.getCurrentTime() });
				
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
	public static void updateStandard(Object obj){
		try{
			Class clazz = obj.getClass();
			Method method = null;
			method = clazz.getMethod("setSys0UpdTime",String.class);
			method.invoke(obj, new Object[] { EbuyMerchantUtil.getCurrentTime() });
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
			Class clazz = obj.getClass();
			Method method = null;
			method = clazz.getMethod("setSys0DelUser",BigInteger.class);
			
			method = clazz.getMethod("setSys0DelTime",String.class);
			method.invoke(obj, new Object[] { EbuyMerchantUtil.getCurrentTime() });
			
			method = clazz.getMethod("setSys0DelState",Integer.class);
			method.invoke(obj, new Object[] { 1 });
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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
	
	/**
	 * 获取当前时间
	 * @param type{1==年,2==月,3==日,4==时}
	 * @param amount增量值
	 * @return String
	 * */
	public static String getNextTime(int type, int amount){
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(getNowTime());
			if(type==1){
				cal.add(Calendar.YEAR, amount);
			}else if(type==2){
				cal.add(Calendar.MONTH, amount);
			}else if(type==3){
				cal.add(Calendar.DATE, amount);
			}else if(type==4){
				cal.add(Calendar.HOUR_OF_DAY, amount);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(cal.getTime());
		}catch(Exception e){
			logger.error(e);
			return null;
		}	
	}
	
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
//			List<String> mobiles = new ArrayList<String>();
//			mobiles.add(mobile);
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
	 * 拿图片的完整路径
	 */
	public static String getPicUrl(String basePath, String picName, Date updTime) {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		IFileServerService fileServerService = (IFileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");
		
		String iconBasePath = sysParamManager.getSysParaValue(basePath);
		String[] tempBp = iconBasePath.split(";");
		iconBasePath = tempBp[0];
		
		return fileServerService.getAccessUrl(iconBasePath + picName, updTime);
	}
	
	public static Date getNowTime(){
		IDualDao dualDao = (IDualDao)CnfantasiaCommbusiUtil.getBeanManager("dualDao");
		String nowTime = dualDao.getNowTime();
		try {
			return DateUtil.formatSecond.get().parse(nowTime);
		} catch (ParseException e) {
			throw new BusinessRuntimeException(e);
		}
	}
	
	public static String getSysParaValue(String key) {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		return sysParamManager.getSysParaValue(key);
	}
	
	public static BigInteger getUuid(String tableName) {
		IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
		return uuidManager.getNextUuidBigInteger(tableName);
	}
}
