/**   
* Filename:    CompanyService.java   
* @version:    1.0  
* Create at:   2015年5月6日 上午8:49:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.company.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;

/**
 * Filename:    CompanyService.java
 * @version:    1.0.0
 * Create at:   2015年5月6日 上午8:49:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月6日       shiyl             1.0             1.0 Version
 */
public class CompanyService implements ICompanyService{
	
	private ISysParamParser companyInfoParamParser;
	public void setCompanyInfoParamParser(ISysParamParser companyInfoParamParser) {
		this.companyInfoParamParser = companyInfoParamParser;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	@Override
	public CompanyInfoConfig getCompanyServiceInfo() {
		CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
		return companyInfoConfig;
	}

	@Override
	public String getJfqQrcodeUrl() {
		String jfqQrcodeUrl = fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.JIEFANGQU_QRCODE_PICPATH), "");
		return jfqQrcodeUrl;
	}

}
