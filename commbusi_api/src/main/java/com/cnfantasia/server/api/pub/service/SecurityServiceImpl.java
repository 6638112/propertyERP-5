/**   
* Filename:    SecurityServiceImpl.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午10:47:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.service;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.login.dao.ILoginDao;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.pub.entity.SysUser;
import com.cnfantasia.server.business.pub.service.ISecurityService;

/**
 * Filename:    SecurityServiceImpl.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午10:47:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
@Service
public class SecurityServiceImpl implements ISecurityService<SysUser>{
	private ILoginDao loginDao;
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public SysUser getUserByUserName(String loginName, Long loginType) {
		LoginNoEntity loginNoEntity=loginDao.selectLoginNoEntityByAccountSupportBind(loginName, loginType);
		if(loginNoEntity==null){return null;}
		SysUser sysUser = new SysUser(loginNoEntity);
		return sysUser;
	}
}
