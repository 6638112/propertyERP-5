/**   
* Filename:    SimplePrizeArea.java   
* @version:    1.0  
* Create at:   2014年7月8日 上午6:19:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.prize.util.DiscountMaker;

/**
 * Filename:    SimplePrizeArea.java
 * @version:    1.0.0
 * Create at:   2014年7月8日 上午6:19:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月8日       shiyl             1.0             1.0 Version
 */
public class SimplePrizeArea extends AbstractPrizeArea{
	
	/**
	 * 构造方法
	 * @param prizeInitCount
	 * @param discountInterval
	 */
	public SimplePrizeArea(int prizeInitCount,DiscountInterval discountInterval){
		this.leftCount=prizeInitCount;
		this.discountInterval=discountInterval;
		this.prizeInfo = DiscountMaker.getDiscountList(prizeInitCount, discountInterval);
	}
	
	public SimplePrizeArea(int prizeInitCount,DiscountValueEntity start,DiscountValueEntity end){
		this(prizeInitCount,new DiscountInterval(start,end));
	}
	
	public SimplePrizeArea(int totalCount,double property,DiscountValueEntity start,DiscountValueEntity end){
		this((int)(totalCount*property), start, end);
	}
	
}
