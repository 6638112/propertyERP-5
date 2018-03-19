/**   
* Filename:    ISecurityService.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午10:47:08   
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

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.ms.pub.entity.SysUser;

/**
 * Filename:    ISecurityService.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午10:47:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
public interface ISecurityService {
	/**
   * 根据用户编号查询User对象
   * 
   * @param loginName 用户编号
   * @param loginType 登录方式
   * @return 对应loginName的User对象，如果不存在，返回null。
   */
  public SysUser getUserByUserName(String loginName);
  
  /**
   * 根据用户Id信息其拥有的权限(角色Ids)
   * @param userId
   * @return
   */
  public List<BigInteger> getRoleIdsByUserId(BigInteger userId);
  
  public List<EbuySupplyMerchant> selectSupplyMerchantListByUserId(BigInteger userId);
  
  public BigInteger selectPropertyCompanyIdByUserId(BigInteger userId);
  
  public List<BigInteger> selectGroupbuildingIdList(BigInteger userId);
  
  public BigInteger selectPropertyCompanyId(BigInteger userId);
  
}
