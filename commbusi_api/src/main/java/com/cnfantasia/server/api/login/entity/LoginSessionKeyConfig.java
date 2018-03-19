/**   
* Filename:    LoginSessionKeyConfig.java   
* @version:    1.0  
* Create at:   2014年6月11日 上午3:06:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

/**
 * Filename:    LoginSessionKeyConfig.java
 * @version:    1.0.0
 * Create at:   2014年6月11日 上午3:06:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月11日       shiyl             1.0             1.0 Version
 */
public class LoginSessionKeyConfig {
	/**
	 * 登录的Session有效期(分钟)
	 */
	private Integer expiredMinute;
	
	public LoginSessionKeyConfig(){}
	public LoginSessionKeyConfig(Integer expiredMinute){
		this.expiredMinute = expiredMinute;
	}
	/**
	 * 获取毫秒数
	 * @return
	 */
	public Long getExpiredMinuteTimeMillions(){
		return expiredMinute*60*1000L;
	}
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("expiredMinute=").append(expiredMinute).append(";");
		return sbf.toString();
	}
	
	public Integer getExpiredMinute() {
		return expiredMinute;
	}

	public void setExpiredMinute(Integer expiredMinute) {
		this.expiredMinute = expiredMinute;
	}
	
}
