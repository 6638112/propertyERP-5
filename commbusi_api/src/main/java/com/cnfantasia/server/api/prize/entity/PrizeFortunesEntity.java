/**   
* Filename:    PrizeFortunesEntity.java   
* @version:    1.0  
* Create at:   2015年2月2日 上午9:13:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import java.util.List;

import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.commbusi.plotproperty.entity.ISectionDateMulti;

/**
 * Filename:    PrizeFortunesEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月2日 上午9:13:22
 * Description: 折扣运势信息实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月2日       shiyl             1.0             1.0 Version
 */
public class PrizeFortunesEntity {
	/**物业周期起止时间*/
	private ISectionDateMulti propStartEndDate;
	
	/**最低折扣记录*/
	private PrizeRecordEntity leastPrizeRecord;
//	/**最低折扣用户*/
//	public UserSimpleEntity getLeastDiscountUser(){
//		if(leastPrizeRecord!=null){
//			return leastPrizeRecord.getPrizeUser();
//		}
//		return null;
//	}
	
	/**本月折扣列表*/
	private List<PrizeRecordEntity> prizeList;
	
	/**最大抽奖次数用户及次数信息*/
	private PrizeCountWithUserEntity maxPrizeCountUserEntity;
	
	/**当前用户本月抽奖次数*/
	private PrizeCountWithUserEntity currPrizeCountUser;
	
	/**门牌所有抽奖对应的物业起止月份*/
	private StartEndDate firstLastPropMonth;
	
	public PrizeFortunesEntity(ISectionDateMulti propStartEndDate,PrizeRecordEntity leastPrizeRecord,List<PrizeRecordEntity> prizeList
			,PrizeCountWithUserEntity maxPrizeCountUserEntity,PrizeCountWithUserEntity currPrizeCountUser
			,StartEndDate firstLastPropMonth){
		this.propStartEndDate = propStartEndDate;
		this.leastPrizeRecord = leastPrizeRecord;
		this.prizeList = prizeList;
		this.maxPrizeCountUserEntity = maxPrizeCountUserEntity;
		this.currPrizeCountUser = currPrizeCountUser;
		this.firstLastPropMonth = firstLastPropMonth;
	}

	public ISectionDateMulti getPropStartEndDate() {
		return propStartEndDate;
	}

	public PrizeRecordEntity getLeastPrizeRecord() {
		return leastPrizeRecord;
	}

	public List<PrizeRecordEntity> getPrizeList() {
		return prizeList;
	}

	public PrizeCountWithUserEntity getMaxPrizeCountUserEntity() {
		return maxPrizeCountUserEntity;
	}

	public PrizeCountWithUserEntity getCurrPrizeCountUser() {
		return currPrizeCountUser;
	}

	public StartEndDate getFirstLastPropMonth() {
		return firstLastPropMonth;
	}
	
}
