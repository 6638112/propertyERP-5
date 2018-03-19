package com.cnfantasia.server.ms.pub.springSecurity;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.ms.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.ms.login.service.ILoginService;
import com.cnfantasia.server.ms.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.ms.pub.entity.SysUser;
import com.cnfantasia.server.ms.pub.service.ISecurityService;
import com.cnfantasia.server.ms.pub.session.SessionManager;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

/**
 * 描述: 从数据库读取用户信息，包括密码、角色、状态等信息
 * 
 */

@Service
public class SimpleUserDetailService implements UserDetailsService {
	private Log logger = LogFactory.getLog(getClass());
  private ISecurityService securityService; 
	public void setSecurityService(ISecurityService securityService) {
		this.securityService = securityService;
	}
	private ILoginService loginService;
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	/**
   * 装配UserDetails对象
   */
  public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException, DataAccessException {
  	HttpServletRequest request = SessionManager.getRequest();
  	//1.搜集参数
		SimpleLoginInfo simpleLoginInfo = SimpleLoginInfo.parseLoginInfo(request);
		//2.交互
		OmsUser loginRes = loginService.login(simpleLoginInfo);
		if(loginRes==null){
			logger.debug(account+" of loginInfo is null.");
			return null;
		}
    //3、返回
  	//此时账号校验已经通过
		SysUser sysUser = new SysUser(loginRes);
		//加载权限信息
  	List<BigInteger> roleIds=securityService.getRoleIdsByUserId(loginRes.getId());
  	sysUser.setRoleIds(roleIds);
  	SimpleUserDetails userDetails = new SimpleUserDetails(sysUser, simpleLoginInfo.getNumber(), simpleLoginInfo.getPassword());
    return userDetails;
  }
}