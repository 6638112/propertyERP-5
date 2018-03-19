package com.cnfantasia.server.api.pub.session;

import java.math.BigInteger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.api.pub.entity.SysUser;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;

public class UserContext {

	/**
	 * 获取用户Id
	 * @return
	 */
	public static String getOperId() {
		LoginNoEntity loginNoEntity = getCurrLoginNo();
		if (loginNoEntity == null) {
			return null;
		}
		UserEntity userEntity = loginNoEntity.getUserEntity();
		if (userEntity == null) {
			return null;
		}
		BigInteger id = userEntity.getId();
		if (id == null) {
			return null;
		}
		return id.toString();
	}
	public static BigInteger getOperIdMustExistBigInteger() {
		BigInteger userId = getOperIdBigInteger();
		if (userId == null) {
			throw new TimeOutRuntimeException();
		}
		return userId;
	}

	public static BigInteger getOperIdBigInteger() {
		String operId = getOperId();
		if (StringUtils.isEmpty(operId)) {
			return null;
		}
		return new BigInteger(operId);
	}

	/**
	 * 判断当前上下文中是否包含用户数据
	 * @return
	 */
	public static boolean isUserInContext() {
		SysUser sysUser = getUser();
		LoginNoEntity loginNoEntity = sysUser == null ? null : sysUser.getLoginNoEntity();
		if (loginNoEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取用户象对象
	 * @return
	 */
	public static SysUser getUser() {
		SysUser userobj = null;
		// 获取SecurityContext上下文
		SecurityContext scontext = SecurityContextHolder.getContext();
		if (scontext == null) {
			return userobj;
		}
		Authentication sauth = scontext.getAuthentication();
		if (sauth == null) {
			return userobj;
		}
		Object principal = sauth.getPrincipal();
		if (principal == null) {
			return userobj;
		}
		if (principal instanceof UserDetails) {
			userobj = ((SimpleUserDetails) principal).getSysUser();
		}
		return userobj;
	}

	public static LoginNoEntity getCurrLoginNo() {
		// LoginNoEntity loginNoEntity = (LoginNoEntity)
		// SessionManager.getSession().getAttribute(SessionManager.USER_CONTEXT_SESSION_KEY);
		// return loginNoEntity;
		SysUser sysUser = getUser();
		LoginNoEntity loginNoEntity = sysUser == null ? null : sysUser.getLoginNoEntity();
		return loginNoEntity;
	}
	
	/**
	 * 默认返回女
	 * @return 0男， 1女
	 */
	public static int getSex() {
		LoginNoEntity loginNo = getCurrLoginNo(); //.getUserEntity().getSex()
		if(loginNo != null && loginNo.getUserEntity() != null) {
			String sex = loginNo.getUserEntity().getSex();
			if(sex == null) {
				return 2;
			} else if(sex.equals("男") || sex.equals("0")) {
				return 0;
			} else if(sex.equals("女") || sex.equals("1")) {
				return 1;
			}
		}
		return 2;
	}
	
	public static BigInteger getCurrLoginNoId(){
		LoginNoEntity loginNoEntity = getCurrLoginNo();
		return loginNoEntity==null?null:loginNoEntity.getId();
	}
	
}
