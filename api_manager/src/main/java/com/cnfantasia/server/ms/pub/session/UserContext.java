package com.cnfantasia.server.ms.pub.session;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.ms.pub.entity.SysUser;

public class UserContext {

	/**
	 * 获取用户Id
	 * 
	 * @return
	 */
	public static String getOperId() {
		if(getUser() != null && getUser().getPropertyCompanyAdminId() != null) {
			return getUser().getPropertyCompanyAdminId().toString();
		}
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
	
	public static BigInteger getOperIdBigIntegerMustExist() {
		if(getUser() != null && getUser().getPropertyCompanyAdminId() != null) {
			return getUser().getPropertyCompanyAdminId();
		}
		String operId = getOperId();
		if (StringUtils.isEmpty(operId)) {
			throw new TimeOutRuntimeException();
		}
		return new BigInteger(operId);
	}
	public static BigInteger getOperIdBigInteger() {
		if(getUser() != null && getUser().getPropertyCompanyAdminId() != null) {
			return getUser().getPropertyCompanyAdminId();
		}
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
	
	public static List<EbuySupplyMerchant> getSupplyMerchantList() {
		SysUser sysUser = getUser();
		return sysUser==null ? null : sysUser.getSupplyMerchantList();
	}
	
	public static List<BigInteger> getSupplyMerchantIdList() {
		List<BigInteger> smIdList = new ArrayList<BigInteger>();
		SysUser sysUser = getUser();
		if(sysUser != null && sysUser.getSupplyMerchantList() != null) {
			for(EbuySupplyMerchant sm : sysUser.getSupplyMerchantList()) {
				smIdList.add(sm.getId());
			}
		}
		return smIdList;
	}
	
	public static List<BigInteger> getGbIdList() {
		SysUser sysUser = getUser();
		if(sysUser != null 
				&& sysUser.getOmsUser()!=null 
				&& sysUser.getOmsUser().getIsadmin()!=null
				&&sysUser.getOmsUser().getIsadmin()==1) {
			return null;// 其它地方根据“return null”判断是否为管理员
		}
		return sysUser==null ? null : sysUser.getGbIdList();
	}

}
