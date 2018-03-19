/**   
* Filename:    IRedenvelopeService.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午3:13:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.redenvelope.entity.HbConvertConsumEntity;
import com.cnfantasia.server.api.redenvelope.entity.HbConvertRuleDescEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeDetailEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeEntity;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbBalanceEntity;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbDiscountEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYearSignal;

/**
 * Filename:    IRedenvelopeService.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午3:13:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public interface IRedenvelopeService {
//	/**
//	 * 查询剩余多少天即可兑换粮票，如果可兑换或者当前月份没有折扣则返回null
//	 * @param userId
//	 * @return
//	 */
////	public Integer getLeftDay2Convert(BigInteger userId);
//	public Integer getLeftDay2Convert(BigInteger userId,String yearMonth);
	/**
	 * 根据月份查询最低折扣
	 * @param userId
	 * @param month
	 * @return
	 */
	SimpleHbDiscountEntity getLastDiscountByMonth(BigInteger userId, IBusinessMonthYearSignal monthYearWithGB);
	/**
	 * 执行粮票兑换
	 * @param userId
	 * @param month
	 * @return
	 */
	SimpleHbDiscountEntity doDiscount2hb(BigInteger userId, IBusinessMonthYear monthYearWithGB);
	
	/**
	 * 查询粮票使用的金额统计信息
	 * @param userId
	 * @return
	 */
	SimpleHbBalanceEntity getAllBalanceCollectInfo(BigInteger userId, int type);
	
	/**
	 * 查询粮票的兑换记录
	 *
     * @param type
     * @param userId
     * @param pageModel
     * @return
	 */
	List<PayRedEnvelopeEntity> getRecord2hbRecord(BigInteger userId);
	List<PayRedEnvelopeEntity> getRecord2hbRecord(BigInteger userId, int type, PageModel pageModel);
	
	/**
	 * 查询粮票的消费记录
	 * @param userId
	 * @return
	 */
	List<PayRedEnvelopeDetailEntity> getHbConsumRecord(BigInteger userId);
	List<PayRedEnvelopeDetailEntity> getHbConsumRecord(BigInteger userId, int type, PageModel pageModel);
	
	/**
	 * 查询粮票的兑换和消费记录
	 * @param userId
	 * @return
	 */
	List<HbConvertConsumEntity> getHbConvertConsumBothRecord(BigInteger userId);
	List<HbConvertConsumEntity> getHbConvertConsumBothRecord(BigInteger userId, PageModel pageModel);
	
//	/**
//	 * 自动兑换粮票
//	 */
//	public void autoConvert2Hb();
	
	List<HbConvertRuleDescEntity> getHbConvertRule();
	
	/**
	 * 神码行动奖励粮票
	 * @author huangzj
	 * @param userId
	 * @param roomId
	 * @param fromId
	 * @param bonus
	 * */
	void appendShenmaHb(BigInteger userId, BigInteger roomId, BigInteger fromId, Long bonus);

    List<SimpleHbBalanceEntity> getHbBalanceEntityList(BigInteger userId);
}
