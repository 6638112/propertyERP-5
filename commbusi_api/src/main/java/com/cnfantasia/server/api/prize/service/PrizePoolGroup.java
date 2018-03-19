/**   
* Filename:    PrizePoolGroup.java   
* @version:    1.0  
* Create at:   2015年1月16日 上午6:37:17   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.MultiPrizeArea;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * Filename:    PrizePoolGroup.java
 * @version:    1.0.0
 * Create at:   2015年1月16日 上午6:37:17
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月16日       shiyl             1.0             1.0 Version
 */
public class PrizePoolGroup extends AbstractPrizePoolGroup{
	private Log logger = LogFactory.getLog(getClass());
	
	/**已签约小区的奖池*/
	private IPrizePool prizePoolSign;
	public void setPrizePoolSign(IPrizePool prizePoolSign) {
		this.prizePoolSign = prizePoolSign;
	}

	/**未签约小区的奖池*/
	private IPrizePool prizePoolUnSign;
	public void setPrizePoolUnSign(IPrizePool prizePoolUnSign) {
		this.prizePoolUnSign = prizePoolUnSign;
	}

	public PrizePoolGroup() {
	}

	public PrizePoolGroup(IPrizePool prizePoolSign, IPrizePool prizePoolUnSign){
		this.prizePoolSign = prizePoolSign;
		this.prizePoolUnSign = prizePoolUnSign;
		this.prizePoolList = new ArrayList<IPrizePool>();
		prizePoolList.add(prizePoolSign);
		prizePoolList.add(prizePoolUnSign);
		logger.info(getClass()+".init.PrizePoolGroup(IPrizePool prizePoolUnSign,IPrizePool prizePoolSign)");
	}

	@Override
	public Long getPrizeCountTotal(boolean signStatus) {
		if(signStatus){
			return prizePoolSign.getPrizeCountTotal();
		}else{
			return prizePoolUnSign.getPrizeCountTotal();
		}
	}
	
	@Override
	public PrizeResultDiscountEntity doPrize(Boolean firstPrize, int onLineDays, int totalPrizeTimes,boolean signStatus,PrizeRecord currLeastPrizeRecord) {
		if(signStatus){
			return prizePoolSign.doPrize(firstPrize, onLineDays, totalPrizeTimes,currLeastPrizeRecord);
		}else{
			return prizePoolUnSign.doPrize(firstPrize, onLineDays, totalPrizeTimes,currLeastPrizeRecord);
		}
	}

	@Override
	public PrizeRuleGenerateConfigEntity getCurrPrizeRuleGenerateConfig(boolean signStatus) {
		if(signStatus){
			return prizePoolSign.getCurrPrizeRuleGenerateConfig();
		}else{
			return prizePoolUnSign.getCurrPrizeRuleGenerateConfig();
		}
	}

	@Override
	public MultiPrizeArea getMultiPrizeArea(boolean signStatus) {
		if(signStatus){
			return prizePoolSign.getMultiPrizeArea();
		}else{
			return prizePoolUnSign.getMultiPrizeArea();
		}
	}

	@Override
	public int insertPrizeData(List<DiscountValueEntity> allDiscountValueList,boolean signStatus) {
		if(signStatus){
			return prizePoolSign.insertPrizeData(allDiscountValueList);
		}else{
			return prizePoolUnSign.insertPrizeData(allDiscountValueList);
		}
	}
	
}
