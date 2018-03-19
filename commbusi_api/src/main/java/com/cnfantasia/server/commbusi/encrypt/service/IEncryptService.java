/**   
* Filename:    IEncryptService.java   
* @version:    1.0  
* Create at:   2016年2月19日 上午10:44:14   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年2月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.encrypt.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.commbusi.encrypt.entity.EncryptUrlEntity;

/**
 * Filename:    IEncryptService.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 上午10:44:14
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
public interface IEncryptService {
	
	/**
	 * 刷新缓存
	 */
	public void freshCache();
	
	/**
	 * 查询所有加密的url
	 * @return
	 */
	public List<EncryptUrlEntity> getEncryptUrlAll();
	
	/**
	 * 根据url,version获取配置的加密信息
	 * @param url
	 * @param version
	 * @return
	 */
	public EncryptUrlEntity getEncrypt(String url,Long version);
	
	/**
	 * 验证加密
	 * @param url
	 * @param paramMap
	 * @param signType
	 * @param toValidSign
	 * @return true验证通过,false验证失败
	 */
	public boolean validate(String url,Map<String,String> paramMap,Integer signType,String toValidSign);
	
}
