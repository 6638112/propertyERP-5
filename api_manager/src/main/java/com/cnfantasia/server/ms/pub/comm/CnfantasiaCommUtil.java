package com.cnfantasia.server.ms.pub.comm;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.commonBusiness.util.SendSmsRunnable;
import com.cnfantasia.server.business.pub.CommBaseEntity;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsPermiRole.service.IOmsPermiRoleBaseService;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service.IOmsUserHasTOmsPermiRoleBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;
import com.cnfantasia.server.ms.propertyManagement.service.IPropertyManagementService;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 公用工具类
 * 可以初始化对象新增、修改、删除等操作
 * */
public class CnfantasiaCommUtil implements ApplicationContextAware {
	
	private static Log logger = LogFactory.getLog(CnfantasiaCommUtil.class);
	
	private static ApplicationContext context;//声明一个静态变量保存
	@Override
	public void setApplicationContext(ApplicationContext contex)
	   throws BeansException {
	   this.context=contex;
	}
	public static ApplicationContext getContext(){
	  return context;
	}
	public static Object getBeanManager(String beanName){
		return context.getBean(beanName);
	}
	
	/**
	 * 新增对象初始化，会自动将对象的uuid和添加人，修改人设值
	 * @param commBaseEntity 新增对象
	 * @param tableName 新增对象的表名
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void newStandard(CommBaseEntity commBaseEntity, String tableName) {
		BigInteger userId = getCurrentUserId();
		try {
			Method method = null;
			Class clazz = commBaseEntity.getClass();
			IUuidManager uuidManager = (IUuidManager) CnfantasiaCommUtil.getBeanManager("uuidManager");
			method = clazz.getMethod("setId", BigInteger.class);
			method.invoke(commBaseEntity, new Object[] { uuidManager.getNextUuidBigInteger(tableName) });
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		if(userId!= null){
			commBaseEntity.setSys0AddUser(userId);
			commBaseEntity.setSys0UpdUser(userId);
		}
		commBaseEntity.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());
		commBaseEntity.setSys0UpdTime(CnfantasiaCommUtil.getCurrentTime());
		commBaseEntity.setSys0DelState(0);
	}
	/**
	 * 新增多个对象初始化，会自动将对象的uuid和添加人，修改人设值
	 * @param objs 新增对象集合
	 * @param tableName 新增对象的表名
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void newStandardList(List<? extends CommBaseEntity> objs, String tableName) {
		try{
			IUuidManager uuidManager = (IUuidManager)CnfantasiaCommUtil.getBeanManager("uuidManager");
			List<BigInteger> objIds = uuidManager.getNextUuidBigInteger(tableName, objs.size());
			Method method = null;
			CommBaseEntity commBaseEntity = null;
			BigInteger userId = getCurrentUserId();
			for (int i = 0; i < objs.size(); i++) {
				commBaseEntity = objs.get(i);
				Class clazz = commBaseEntity.getClass();
				method = clazz.getMethod("setId",BigInteger.class);
				method.invoke(commBaseEntity, new Object[] { objIds.get(i) });

				if(userId!= null){
					commBaseEntity.setSys0AddUser(userId);
					commBaseEntity.setSys0UpdUser(userId);
				}
				commBaseEntity.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());
				commBaseEntity.setSys0UpdTime(CnfantasiaCommUtil.getCurrentTime());
				commBaseEntity.setSys0DelState(0);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改对象初始化，会自动将对象的修改人设值
	 * @param commBaseEntity 新增对象
	 * */
	public static void updateStandard(CommBaseEntity commBaseEntity) {
		BigInteger userId = getCurrentUserId();
		if (userId != null) {
			commBaseEntity.setSys0UpdUser(userId);
		}
		commBaseEntity.setSys0UpdTime(null);
		commBaseEntity.setSys0DelState(0);
	}
	
	/**
	 * 删除对象初始化，会自动将对象的删除人和删除状态设值
	 * @param commBaseEntity 新增对象
	 * */
	public static void deleteStandard(CommBaseEntity commBaseEntity) {
		BigInteger userId = getCurrentUserId();
		if (userId != null) {
			commBaseEntity.setSys0DelUser(userId);
			commBaseEntity.setSys0DelTime(CnfantasiaCommUtil.getCurrentTime());
		}
		commBaseEntity.setSys0DelState(1);
	}

	/**
	 * 获取当前用户的ID
	 * */
	public static BigInteger getCurrentUserId(){
		return UserContext.getOperIdBigInteger();
	}
	
	/**
	 * 获取当前时间
	 * @return String
	 * */
	public static String getCurrentTime(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(new Date());
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
	 * 转换类型为Integer
	 * */
	public static Integer convert2Integer(Object obj){
		if(obj == null){
			return null;
		}else{
			try{
				return Integer.valueOf(obj.toString());
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
			String isMessageSend = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
			FutureTask<String> task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, isMessageSend));
			new Thread(task).start();
			logger.info("发送短信的响应 = " + task.get());
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 获取用户
	 * */
	public static User getUserById(BigInteger userId){
		try {
			IUserBaseService userBaseService = (IUserBaseService)CnfantasiaCommUtil.getBeanManager("userBaseService");
			return userBaseService.getUserBySeqId(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取用户的默认房号
	 * */
	public static BigInteger getUserDefaultRoomId(BigInteger userId){
		try {
			User user = CnfantasiaCommUtil.getUserById(userId);
			if(null!=user){
				return user.getDefaultRoomId();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取当前用户角色
	 * */
	public static List<OmsUserHasTOmsPermiRole> getCurrentUserRoles(){
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tOmsUserFId", CnfantasiaCommUtil.getCurrentUserId());
			IOmsUserHasTOmsPermiRoleBaseService omsUserHasTOmsPermiRoleBaseService = (IOmsUserHasTOmsPermiRoleBaseService)CnfantasiaCommUtil.getBeanManager("omsUserHasTOmsPermiRoleBaseService");
			return omsUserHasTOmsPermiRoleBaseService.getOmsUserHasTOmsPermiRoleByCondition(paramMap);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取省信息
	 * */
	public static List<ProvinceWithCityBlock> getProvinceWithCityBlockList(){
		try {
			IProvinceCityBlockService provinceCityBlockService = (IProvinceCityBlockService)CnfantasiaCommUtil.getBeanManager("provinceCityBlockService");
			return provinceCityBlockService.getProvinceWithCityBlockList();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取物业公司信息
	 * */
	public static PropertyCompanyWorkbenchEntity getPropertyCompanyByUserId(){
		try {
			IPropertyCompanyService propertyCompanyService = (IPropertyCompanyService)CnfantasiaCommUtil.getBeanManager("propertyCompanyService");
			return propertyCompanyService.getPropertyCompanyWorkbench(CnfantasiaCommUtil.getCurrentUserId());
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取物业公司的管理处信息
	 * */
	public static List<PropertyManagementEntity> getPropertyManagementByCurrentUser(){
		try {
			IPropertyManagementService propertyManagementService = (IPropertyManagementService)CnfantasiaCommUtil.getBeanManager("propertyManagementService");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(null != UserContext.getCurrUser().getIsPmUser() && UserContext.getCurrUser().getIsPmUser() == 1){//管理处账号
				paramMap.put("userId", CnfantasiaCommUtil.getCurrentUserId());
			}else{//物业公司账号
				paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
				paramMap.put("pcAdminId", CnfantasiaCommUtil.getCurrentUserId());
			}
			return propertyManagementService.selectPropertyManagementForList(paramMap);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 根据角色编码获取角色ID
	 * @param roleCode
	 * @return roleId
	 * */
	public static BigInteger getRoleIdByRoleCode(String code){
		try {
			IOmsPermiRoleBaseService omsPermiRoleBaseService = (IOmsPermiRoleBaseService)CnfantasiaCommUtil.getBeanManager("omsPermiRoleBaseService");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", code);
			List<OmsPermiRole> roles = omsPermiRoleBaseService.getOmsPermiRoleByCondition(paramMap);
			if(null!=roles && roles.size()>0){
				return roles.get(0).getId();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
