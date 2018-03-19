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
package com.cnfantasia.server.ms.pub.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.ms.login.dao.ILoginDao;
import com.cnfantasia.server.ms.permi.dao.IPermiDao;
import com.cnfantasia.server.ms.pub.entity.SysUser;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

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
public class SecurityServiceImpl implements ISecurityService{
	private static final BigInteger commRoleId=new BigInteger("1");//公告角色Id为1
	private ILoginDao loginDao;
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
	private IPermiDao permiDao;
	public void setPermiDao(IPermiDao permiDao) {
		this.permiDao = permiDao;
	}

	@Override
	public SysUser getUserByUserName(String loginName) {
		OmsUser loginNoEntity=loginDao.selectOmsUserByAccount(loginName);
		if(loginNoEntity==null){return null;}
		SysUser sysUser = new SysUser(loginNoEntity);
		List<BigInteger> roleIds = getRoleIdsByUserId(loginNoEntity.getId());//注意传入的是用户Id
		sysUser.setRoleIds(roleIds);
		return sysUser;
	}

	@Override
	public List<BigInteger> getRoleIdsByUserId(BigInteger userId) {
		List<BigInteger> roleIds = permiDao.selectRoleIdsByUserId(userId);
		//增加公告的角色Id
		roleIds.add(commRoleId);
		return roleIds;
	}
	
	
}
