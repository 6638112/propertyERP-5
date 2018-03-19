package com.cnfantasia.server.ms.pub.session;

import java.math.BigInteger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.ms.pub.entity.SysUser;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

public class UserContext {

	/**
	 * 获取用户Id
	 * 
	 * @return
	 */
	public static String getOperId() {
		OmsUser loginNoEntity = getCurrUser();
		if (loginNoEntity == null) {
			return null;
		}
		BigInteger id = loginNoEntity.getId();
		if (id == null) {
			return null;
		}
		return id.toString();
	}

	public static BigInteger getOperIdBigInteger() {
		String operId = getOperId();
		if (StringUtils.isEmpty(operId)) {
			return null;
		}
		return new BigInteger(operId);
	}

	/**
	 * 获取用户登录信息
	 * @return
	 */
	public static OmsUser getCurrUser() {
//		LoginNoEntity loginNoEntity = (LoginNoEntity) SessionManager.getSession().getAttribute(SessionManager.USER_CONTEXT_SESSION_KEY);
//		return loginNoEntity;
		SysUser sysUser = getUser();
		return sysUser==null?null:sysUser.getOmsUser();
	}

//	public static void setCurrUser(LoginNoEntity loginNoEntity) {
//		SessionManager.getSession().setAttribute(SessionManager.USER_CONTEXT_SESSION_KEY,loginNoEntity);
//	}

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

}
