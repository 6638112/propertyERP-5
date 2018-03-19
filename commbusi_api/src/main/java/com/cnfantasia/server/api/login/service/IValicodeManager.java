/**   
* Filename:    IValicodeManager.java   
* @version:    1.0  
* Create at:   2014年6月20日 上午10:41:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.service;

import com.cnfantasia.server.api.login.entity.ValicodeEntity;

/**
 * Filename:    IValicodeManager.java
 * @version:    1.0.0
 * Create at:   2014年6月20日 上午10:41:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月20日       shiyl             1.0             1.0 Version
 */
public interface IValicodeManager {
	/**
	 * 生成验证码
	 * @param phone
	 * @param type
	 * @return
	 */
	public String generateValicode(Integer type,String phone);
	
	/**
	 * 存入验证码到Session中
	 * @param type
	 * @param value
	 */
	public  void putValicode2Session(Integer type,String phone,String value);
	
	/**
	 * 通过类型获取验证码
	 * @param type
	 * @return
	 */
	public  ValicodeEntity getValicodefromSession(Integer type);
	
	/**
	 * 通过类型从session中获取验证码实体，并校验结果
	 * @param type
	 * @return
	 */
	public  void checkWithSession(Integer type,String phone,String goalValicode);
	
	/**
	 * 清除验证码
	 * @param type
	 * @return
	 */
	public  void clearValicode(Integer type);
}
