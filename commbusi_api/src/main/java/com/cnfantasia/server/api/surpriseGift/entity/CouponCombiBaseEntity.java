/**   
* Filename:    CouponCombiBaseEntity.java   
* @version:    1.0  
* Create at:   2015年4月17日 上午3:10:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Filename:    CouponCombiBaseEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月17日 上午3:10:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月17日       shiyl             1.0             1.0 Version
 */
public class CouponCombiBaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	public CouponCombiBaseEntity(Double payPercent,Long totalAmount){
		this.payPercent = payPercent;
		this.totalAmount = totalAmount;
	}
	
	/**支付的百分比*/
	private Double payPercent;
	public Double getPayPercent() {
		return payPercent;
	}
//	public void setPayPercent(Double payPercent) {
//		this.payPercent = payPercent;
//	}
	
	
	/**目标计算金额*/
	private Long totalAmount;
	public Long getTotalAmount() {
		return totalAmount;
	}
//	public void setTotalAmount(Long totalAmount) {
//		this.totalAmount = totalAmount;
//	}
	
	/**最大可优惠的金额*/
	public Long getMaxCouponAmount(){
		return calculateMaxCouponAmount(totalAmount, payPercent);
	}
	
	public static Long calculateMaxCouponAmount(Long totalAmount,Double payPercent){
		Long goalAmount = 0L;
		if(totalAmount!=null&&payPercent!=null){
//			goalAmount = (long) (totalAmount*payPercent);
			BigDecimal tmpDeci = new BigDecimal(totalAmount).multiply(new BigDecimal(payPercent*100)).divide(new BigDecimal(100), 0, RoundingMode.HALF_EVEN);
			goalAmount = tmpDeci.longValue();
		}
		return goalAmount;
	}
	
//	public static void main(String[] args) {
//		System.out.println(calculateMaxCouponAmount(20L, 0.04));
//	}
	
}
