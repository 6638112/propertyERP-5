/**   
* Filename:    EbuyAuthPicConfig.java   
* @version:    1.0  
* Create at:   2014年6月15日 上午7:47:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.omsCommonSysParam.entity;

/**
 * Filename:    EbuyAuthPicConfig.java
 * @version:    1.0.0
 * Create at:   2014年6月15日 上午7:47:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月15日       shiyl             1.0             1.0 Version
 */
public class EbuyAuthPicConfig {
	/**
	 * 认证图标相对根地址
	 */
	private String authIcoBasePath;
	/**
	 * 产品认证图片相对根地址
	 */
	private String picAuthFilePath;
	public EbuyAuthPicConfig(String authIcoBasePath,String picAuthFilePath){
		this.authIcoBasePath = authIcoBasePath;
		this.picAuthFilePath = picAuthFilePath;
	}
	
	public String getAuthIcoBasePath() {
		return authIcoBasePath;
	}
	public void setAuthIcoBasePath(String authIcoBasePath) {
		this.authIcoBasePath = authIcoBasePath;
	}
	public String getPicAuthFilePath() {
		return picAuthFilePath;
	}
	public void setPicAuthFilePath(String picAuthFilePath) {
		this.picAuthFilePath = picAuthFilePath;
	}
	
}
