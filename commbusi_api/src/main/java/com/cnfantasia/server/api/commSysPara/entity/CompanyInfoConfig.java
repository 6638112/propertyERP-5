/**   
* Filename:    CompanyInfoConfig.java   
* @version:    1.0  
* Create at:   2014年6月11日 下午12:14:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.entity;

import java.io.Serializable;

/**
 * Filename:    CompanyInfoConfig.java
 * @version:    1.0.0
 * Create at:   2014年6月11日 下午12:14:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月11日       shiyl             1.0             1.0 Version
 */
public class CompanyInfoConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 手机号
	 */
	private String tel;
	/**
	 * 用户Q群
	 */
	private String qqGroup;
	/**
	 * 客服电话
	 */
	private String serviceTel;
	public CompanyInfoConfig(){}
	public CompanyInfoConfig(String tel,String qqGroup,String serviceTel){
		this.tel = tel;
		this.qqGroup = qqGroup;
		this.serviceTel = serviceTel;
	}
	
	
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("tel=").append(tel).append(";");
		sbf.append("qqGroup=").append(qqGroup).append(";");
		sbf.append("serviceTel=").append(serviceTel).append(";");
		return sbf.toString();
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getQqGroup() {
		return qqGroup;
	}
	public void setQqGroup(String qqGroup) {
		this.qqGroup = qqGroup;
	}
	
	public String getServiceTel() {
		return serviceTel;
	}
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	
	
}
