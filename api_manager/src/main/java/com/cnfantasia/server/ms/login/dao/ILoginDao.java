/**   
* Filename:    ILoginDao.java   
* @version:    1.0  
* Create at:   2014年5月6日 上午9:11:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.login.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

/**
 * Filename:    ILoginDao.java
 * @version:    1.0.0
 * Create at:   2014年5月6日 上午9:11:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月6日       shiyl             1.0             1.0 Version
 */
public interface ILoginDao {
	/**
	 * 通过账号，密码，类型，查询登录信息
	 * @param number
	 * @param password
	 * @param loginType
	 * @return
	 */
	public OmsUser selectOmsUser(String number,String password);

	/**
	 * 根据登录名查询用户信息
	 * @param loginName
	 * @return
	 */
	public OmsUser selectOmsUserByAccount(String loginName);
	
	public List<EbuySupplyMerchant> selectSupplyMerchantListByUserId(BigInteger userId);
	
	public BigInteger selectPropertyCompanyIdByUserId(BigInteger userId);
	
	public List<BigInteger> selectGroupbuildingIdList(BigInteger userId);
	
	public BigInteger selectPropertyCompanyId(BigInteger userId);
	
}
