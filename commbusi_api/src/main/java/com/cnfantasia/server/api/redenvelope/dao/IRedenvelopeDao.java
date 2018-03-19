/**   
* Filename:    IRedenvelopeDao.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午3:13:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.redenvelope.entity.HbConvertConsumEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeDetailEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    IRedenvelopeDao.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午3:13:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public interface IRedenvelopeDao {
	
//	/**
//	 * 按月份查询已经兑换的粮票数量
//	 * @param userId
//	 * @param month
//	 * @return
//	 */
//	public int selectIsConvertCount(BigInteger userId,String month);
	
	
	/**
	 * 查询粮票已兑换总金额
	 * @param userId
	 * @return
	 */
	Long selectTotalConvertMoney(BigInteger userId);
	/**
	 * 查询粮票消费总金额
	 * @param userId
	 * @return
	 */
	Long selectTotalConsumMoney(BigInteger userId);
	
	/**
	 * 查询粮票的兑换记录
	 * @param userId
	 * @return
	 */
	List<PayRedEnvelopeEntity> selectRecord2hbRecord(BigInteger userId);

	List<PayRedEnvelopeEntity> selectRecord2hbRecord(BigInteger userId, int type, PageModel pageModel);
	
	/**
	 * 查询粮票的消费记录
	 * @param userId
	 * @return
	 */
	List<PayRedEnvelopeDetailEntity> selectHbConsumRecord(BigInteger userId);
	List<PayRedEnvelopeDetailEntity> selectHbConsumRecord(BigInteger userId, int type, PageModel pageModel);
	
	/**
	 * 查询粮票的兑换和消费记录
	 * @param userId
	 * @return
	 */
	List<HbConvertConsumEntity> selectHbConvertConsumBothRecord(BigInteger userId);
	List<HbConvertConsumEntity> selectHbConvertConsumBothRecord(BigInteger userId, PageModel pageModel);
	
//	/**
//	 * 查询所有可兑换粮票的用户Id
//	 */
//	public List<BigInteger> selectAllConvertUserIds();
	
	/**
	 * 根据折扣查询对应可兑换的粮票
	 * @param discount
	 * @return
	 */
	Long selectMoneyByDiscount(Double discount);
	
}
