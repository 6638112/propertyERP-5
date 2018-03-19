/**   
* Filename:    ApplyBaseEntity.java   
* @version:    1.0  
* Create at:   2014年5月21日 下午1:56:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

import java.io.Serializable;

/**
 * Filename:    ApplyBaseEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月21日 下午1:56:54
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月21日       shiyl             1.0             1.0 Version
 */
public class ApplyBaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**验证服务的url*/
	protected String url;
	/**申请的应用Key*/
	protected String appKey;
	/**申请的key对应的密钥*/
	protected String secret;
	public ApplyBaseEntity(){
		
	}
	/**
	 * 
	 * @param url 验证服务的url
	 * @param appKey 申请的应用Key
	 * @param secret 申请的key对应的密钥
	 */
	public ApplyBaseEntity(String url,String appKey,String secret){
		this.url = url;
		this.appKey = appKey;
		this.secret = secret;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
