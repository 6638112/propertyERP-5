/**   
* Filename:    PropertyFeeCalculator.java   
* @version:    1.0  
* Create at:   2014年8月18日 上午7:28:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Filename:    PropertyFeeCalculator.java
 * @version:    1.0.0
 * Create at:   2014年8月18日 上午7:28:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月18日       shiyl             1.0             1.0 Version
 */
public class PropertyFeeCalculator {
	
//	/**
//	 * TODO .. 小数点精确度的问题
//	 * 计算物业账单优惠的金额
//	 * @param srcAmount 原始物业费
//	 * @param discount 使用的折扣 9.5
//	 * @return 节省的金额
//	 */
//	public static Long calculate(Long srcAmount,Double discount){
//		Long gainMoney = (long) (srcAmount*(1-discount/10.0));
//		return gainMoney;
//	}
	
	/**
	 * 四舍五入
	 * @param srcAmount
	 * @param discount
	 * @return
	 */
	public static Double calculate(Double srcAmount,Double discount){
		BigDecimal tmpDeci = new BigDecimal(srcAmount).multiply(new BigDecimal(discount*10)).divide(new BigDecimal(100), 0, RoundingMode.HALF_EVEN);
		BigDecimal gainMoney = new BigDecimal(srcAmount).subtract(tmpDeci);
		return gainMoney.doubleValue();
	}
	
}
