/**   
* Filename:    PropFeeDiscount.java   
* @version:    1.0  
* Create at:   2015年12月21日 下午3:12:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年12月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.plotproperty.entity;

import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * 物业费使用折扣
 * Filename:    PropFeeDiscount.java
 * @version:    1.0.0
 * Create at:   2015年12月21日 下午3:12:11
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年12月21日       shiyl             1.0             1.0 Version
 */
public class PropFeeDiscount extends PayBillSplit{

	private static final long serialVersionUID = 1L;
	
	/**最低折扣信息*/
	private PrizeRecord leastRecord;

	public PrizeRecord getLeastRecord() {
		return leastRecord;
	}

	public void setLeastRecord(PrizeRecord leastRecord) {
		this.leastRecord = leastRecord;
	}
	
	
	
	
}
