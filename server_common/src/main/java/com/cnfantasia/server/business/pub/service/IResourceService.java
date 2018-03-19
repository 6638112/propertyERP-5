/**   
* Filename:    IResourceService.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午10:18:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

/**
 * Filename:    IResourceService.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午10:18:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
public interface IResourceService {
	/**
   * 查询已存在的对应关系：（功能资源:权限（list））
   * @return Map Object。
   */
  public Map<String, Collection<ConfigAttribute>> getAllSourceAndRole();
}
