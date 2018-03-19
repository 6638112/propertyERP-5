/**   
* Filename:    LoginAccNoAndType.java   
* @version:    1.0  
* Create at:   2015年5月7日 上午10:38:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

/**
 * Filename:    LoginAccNoAndType.java
 * @version:    1.0.0
 * Create at:   2015年5月7日 上午10:38:51
 * Description:登录的账号及类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月7日       shiyl             1.0             1.0 Version
 */
public class LoginAccNoAndType {
	
	/**登录账号*/
	private String accountNo;
	/**登录账号类别*/
	private Long accType;
	
	public LoginAccNoAndType(String accountNo,Long accType){
		this.accountNo = accountNo;
		this.accType = accType;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Long getAccType() {
		return accType;
	}
	public void setAccType(Long accType) {
		this.accType = accType;
	}
	
	
}
