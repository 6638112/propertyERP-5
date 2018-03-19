/**   
* Filename:    BaiduPushConfigEntity.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午2:00:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.entity;

/**
 * Filename:    BaiduPushConfigEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午2:00:43
 * Description:百度消息推送的配置
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public class BaiduPushConfigEntity {
	/**请求的url*/
	private String apiUrl;
	/**key*/
	private String apiKey;
	/**密钥*/
	private String secretKey;
	
	public BaiduPushConfigEntity(){}
	/**
	 * 构造方法
	 * @param apiUrl
	 * @param apiKey
	 * @param secretKey
	 */
	public BaiduPushConfigEntity(String apiUrl,String apiKey,String secretKey){
		this.apiUrl = apiUrl;
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}
	
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	
}
