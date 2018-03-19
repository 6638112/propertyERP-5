package com.cnfantasia.server.api.pub.springSecurity;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.api.login.service.ILoginService;
import com.cnfantasia.server.api.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.api.pub.entity.SysUser;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.business.pub.springSecurity.AbstractUserDetailService;

/**
 * 描述: 从数据库读取用户信息，包括密码、角色、状态等信息
 * 
 */

@Service
public class SimpleUserDetailService extends AbstractUserDetailService {
	private Log logger = LogFactory.getLog(getClass());

	private ILoginService loginService;
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	/**
   * 装配UserDetails对象
   */
	@Override
  public UserDetails doLoadUserByUsername(String account) throws UsernameNotFoundException, DataAccessException {
  	HttpServletRequest request = SessionManager.getRequest();
  	//1.搜集参数
		SimpleLoginInfo simpleLoginInfo = SimpleLoginInfo.parseLoginInfo(request);
		Long subChannel = HeaderManager.getSubChannelIdLong();
		String deviceId = HeaderManager.getDeviceId();
		String version = HeaderManager.getVersion();
//		loginService.validateLoginType(simpleLoginInfo);
		//2.交互
		LoginNoEntity loginRes = loginService.login(simpleLoginInfo,subChannel,version,deviceId);
		if(loginRes==null){
			logger.debug(account+" of loginInfo is null.");
			return null;
		}
    //3、返回
  	//此时账号校验已经通过
		SysUser sysUser = new SysUser(loginRes);
		//加载权限信息
//  	List<BigInteger> roleIds=securityService.getRoleIdsByUserId(loginRes.gettUserFId());
//  	sysUser.setRoleIds(roleIds);
  	SimpleUserDetails userDetails = new SimpleUserDetails(sysUser, simpleLoginInfo.getNumber(), simpleLoginInfo.getPassword());//syl-udp 2014-6-16 20:29:47
//  	SimpleUserDetails userDetails = new SimpleUserDetails(sysUser, sysUser.getUsername(), sysUser.getPassword());//syl-udp 2014-6-16 20:29:47
    return userDetails;
  }
	
//	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException, DataAccessException {
////		CommonUtils.safeGetParameter(SessionManager.getRequest(),"ticket")
//		int index = account.indexOf("~");
//		Long accType =  Long.valueOf(account.substring(0,index));
//		String accNo =account.substring(index+1,account.length());
//		LoginNoEntity loginRes = loginService.getLoginNoEntityByAccount(accNo, accType);
//		SysUser sysUser = new SysUser(loginRes);
//		List<BigInteger> roleIds=securityService.getRoleIdsByUserId(loginRes.gettUserFId());
//  	sysUser.setRoleIds(roleIds);
//		SimpleUserDetails userDetails = new SimpleUserDetails(sysUser, account, null);
//		return userDetails;
//	}
	
}