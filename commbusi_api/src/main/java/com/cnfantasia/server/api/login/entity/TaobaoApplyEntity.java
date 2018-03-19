/**   
* Filename:    TaobaoApplyEntity.java   
* @version:    1.0  
* Create at:   2014年5月23日 上午1:27:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

/**
 * Filename:    TaobaoApplyEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月23日 上午1:27:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月23日       shiyl             1.0             1.0 Version
 */
public class TaobaoApplyEntity extends ApplyBaseEntity{
	private static final long serialVersionUID = 1L;
	public static final String FIELDS_OF_GET_USERINFO = "user_id,uid,nick,sex";
	/**
	 * 
	 * @param url 验证服务的url
	 * @param appKey 申请的应用Key
	 * @param secret 申请的key对应的密钥
	 */
	public TaobaoApplyEntity(String url,String appKey,String secret){
		super(url, appKey, secret);
	}
}
