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

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.ms.login.service.ILoginService;
import com.cnfantasia.server.ms.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.ms.pub.entity.SysUser;
import com.cnfantasia.server.ms.pub.service.ISecurityService;
import com.cnfantasia.server.ms.pub.session.SessionManager;

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
  	
  	List<EbuySupplyMerchant> supplyMerchantList = securityService.selectSupplyMerchantListByUserId(loginRes.getId());
  	sysUser.setSupplyMerchantList(supplyMerchantList);
  	sysUser.setSourceUserId(loginRes.getId());
  //	BigInteger propertyCompanyId = securityService.selectPropertyCompanyIdByUserId(loginRes.getId());
  	BigInteger propertyCompanyId = loginRes.getParentUserId();
  	//添加母子账号后，不再需要通过t_oms_user_has_t_property_company给物业公司分配多个账号了，父账号就是他所在物业公司的账号
  	if(propertyCompanyId != null) {
  	  	sysUser.setPropertyCompanyAdminId(propertyCompanyId);
  	}
  	
  	//数据权限控制，取有权限的小区Id
  	List<BigInteger> gbIdList = securityService.selectGroupbuildingIdList(loginRes.getId());
  	for(int i = gbIdList.size()-1; i >= 0; i--) {
  		if(gbIdList.get(i) == null) {
  			gbIdList.remove(i);
  		}
  	}
  	sysUser.setGbIdList(gbIdList);
  	
  	BigInteger pcId = securityService.selectPropertyCompanyId(loginRes.getId());
  	sysUser.setPropertyCompanyId(pcId);
  	
  	SimpleUserDetails userDetails = new SimpleUserDetails(sysUser, simpleLoginInfo.getNumber(), simpleLoginInfo.getPassword());
    return userDetails;
  }
}