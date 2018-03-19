/**   
* Filename:    PropertyGoodNewsRowDataEntity.java   
* @version:    1.0  
* Create at:   2015年8月25日 下午9:38:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.entity;

import java.math.BigInteger;

/**
 * Filename:    PropertyGoodNewsRowDataEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月25日 下午9:38:45
 * Description:物业费喜报数据单行实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月25日       shiyl             1.0             1.0 Version
 */
public class PropertyGoodNewsRowDataEntity{
	/**排名*/
	private Integer order;
	/**楼东号*/
	private String buildingName;
	/**解放号*/
	private BigInteger jfNum;
	/**手机尾号*/
	private String phoneTail;
	/**使用的折扣*/
	private Double discount;
	/**节省的物业费*/
	private Long savedMoney;
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public BigInteger getJfNum() {
		return jfNum;
	}
	public void setJfNum(BigInteger jfNum) {
		this.jfNum = jfNum;
	}
	public String getPhoneTail() {
		return phoneTail;
	}
	public void setPhoneTail(String phoneTail) {
		this.phoneTail = phoneTail;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Long getSavedMoney() {
		return savedMoney;
	}
	public void setSavedMoney(Long savedMoney) {
		this.savedMoney = savedMoney;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
}
