/**   
* Filename:    PrizeRecordEntityWithBusinessMonthYear.java   
* @version:    1.0  
* Create at:   2015年5月22日 上午3:31:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;

/**
 * Filename:    PrizeRecordEntityWithBusinessMonthYear.java
 * @version:    1.0.0
 * Create at:   2015年5月22日 上午3:31:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月22日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordEntityWithBusinessMonthYear extends PrizeRecordEntity{
	private static final long serialVersionUID = 1L;
	
	private PrizeRecordEntity prizeRecordEntity;
	public PrizeRecordEntity getPrizeRecordEntity() {
		return prizeRecordEntity;
	}
	public void setPrizeRecordEntity(PrizeRecordEntity prizeRecordEntity) {
		this.prizeRecordEntity = prizeRecordEntity;
	}
	
	private IBusinessMonthYear businessMonthYearWithGB;
	public IBusinessMonthYear getBusinessMonthYearWithGB() {
		return businessMonthYearWithGB;
	}
	public void setBusinessMonthYearWithGB(IBusinessMonthYear businessMonthYearWithGB) {
		this.businessMonthYearWithGB = businessMonthYearWithGB;
	}
	
	public PrizeRecordEntityWithBusinessMonthYear(PrizeRecordEntity prizeRecordEntity,IBusinessMonthYear businessMonthYearWithGB){
		this.prizeRecordEntity = prizeRecordEntity;
		this.businessMonthYearWithGB = businessMonthYearWithGB;
	}
}
