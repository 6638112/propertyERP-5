/**   
* Filename:    ICompanyService.java   
* @version:    1.0  
* Create at:   2015年5月6日 上午8:43:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.company.service;

import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;

/**
 * Filename:    ICompanyService.java
 * @version:    1.0.0
 * Create at:   2015年5月6日 上午8:43:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月6日       shiyl             1.0             1.0 Version
 */
public interface ICompanyService {
	/**
	 * 获取公司服务信息
	 * @return
	 */
	public CompanyInfoConfig getCompanyServiceInfo();
	
	/**
	 * 获取解放区二维码下载地址
	 * @return
	 */
	public String getJfqQrcodeUrl();
	
}
