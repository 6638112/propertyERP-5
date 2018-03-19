/**   
* Filename:    EbuyDeliveryMethodUtil.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午6:31:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.util;

/**
 * Filename:    EbuyDeliveryMethodUtil.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午6:31:51
 * Description:配送方式出工具类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public class EbuyDeliveryMethodUtil {
	
	/**
	 * 判断对应金额是否在当前区间内[start,end)
	 * @param priceAmount
	 * @return
	 */
	public static boolean fetchIsContain(Long priceAmount,Long amountStart,Long amountEnd){
		if(amountStart!=null&&!checkIsAmountEndNull(amountEnd)&&priceAmount>=amountStart&&priceAmount<amountEnd){
			return true;
		}else if(amountStart!=null&&checkIsAmountEndNull(amountEnd)&&priceAmount>=amountStart){//没有截止金额时
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean checkIsAmountEndNull(Long amountEnd){
		if(amountEnd==null||amountEnd==0){
			return true;
		}else{
			return false;
		}
	}
	
}
