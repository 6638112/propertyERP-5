/**   
* Filename:    HbFromEntity.java   
* @version:    1.0  
* Create at:   2014年6月26日 上午6:24:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    HbFromEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月26日 上午6:24:38
 * Description:粮票来源信息公用存储
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月26日       shiyl             1.0             1.0 Version
 */
public class HbFromEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**id*/
	private BigInteger id;
	/**折扣信息*/
	private String discountInfo;
	/**神码粮票金额*/
	private Long shenMaRecordBonus;
	/**退款粮票金额*/
	private Long refundRedBonus;
	
	public HbFromEntity(BigInteger id,String discountInfo,Long shenMaRecordBonus,Long refundRedBonus){
		this.id = id;
		this.discountInfo = discountInfo;
		this.shenMaRecordBonus = shenMaRecordBonus;
		this.refundRedBonus = refundRedBonus;
	}
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("discountInfo=").append(discountInfo).append(";");
		sbf.append("shenMaRecordBonus=").append(shenMaRecordBonus).append(";");
		sbf.append("refundRedBonus=").append(refundRedBonus).append(";");
		return sbf.toString();
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public String getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}
	
	public Long getShenMaRecordBonus() {
		return shenMaRecordBonus;
	}
	public void setShenMaRecordBonus(Long shenMaRecordBonus) {
		this.shenMaRecordBonus = shenMaRecordBonus;
	}
	
	public Long getRefundRedBonus() {
		return refundRedBonus;
	}
	public void setRefundRedBonus(Long refundRedBonus) {
		this.refundRedBonus = refundRedBonus;
	}
	public HbFromEntity(){
	}
	
}
