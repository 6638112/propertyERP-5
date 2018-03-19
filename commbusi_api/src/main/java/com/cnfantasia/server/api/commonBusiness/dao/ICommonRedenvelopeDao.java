/**   
* Filename:    ICommonRedenvelopeDao.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:52:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * Filename:    ICommonRedenvelopeDao.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:52:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public interface ICommonRedenvelopeDao {
	/**
	 * 查询粮票可用余额
	 * @param userId
	 * @param type 0全国可用，1优选体验店专用
	 * @return
	 */
	Long selectTotalHbBalance(BigInteger userId, int type);
	
	/**
	 * 查询有可用余额的所有粮票列表
	 * @param userId
	 * @param type 0表示全国通用粮票，1表示体验店专用粮票
	 * @return
	 */
	List<PayRedEnvelope> selectPayRedEnvelopeAvailable(BigInteger userId, int type);
	
}
