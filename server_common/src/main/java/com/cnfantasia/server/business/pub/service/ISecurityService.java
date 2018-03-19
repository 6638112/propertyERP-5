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
package com.cnfantasia.server.business.pub.service;

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
public interface ISecurityService <T>{//T SysUser
	/**
   * 根据用户编号查询User对象
   * 
   * @param loginName 用户编号
   * @param loginType 登录方式
   * @return 对应loginName的User对象，如果不存在，返回null。
   */
  public T getUserByUserName(String loginName,Long loginType);
  
}
