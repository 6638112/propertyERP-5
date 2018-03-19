/**   
* Filename:    EbuyExtandBuyParam.java   
* @version:    1.0  
* Create at:   2015年1月12日 上午1:58:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;

/**
 * Filename:    EbuyExtandBuyParam.java
 * @version:    1.0.0
 * Create at:   2015年1月12日 上午1:58:27
 * Description: 购买商品时的扩展信息例如 手机充值时的手机号
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月12日       shiyl             1.0             1.0 Version
 */
public class EbuyExtandBuyParam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**手机号*/
	private String phoneNum;
	/**QQ号*/
	private String qqNum;
	
	public EbuyExtandBuyParam(){}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	
}
