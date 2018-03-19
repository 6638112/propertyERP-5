/**   
* Filename:    ICommonRedenvelopeService.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:52:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

import java.math.BigInteger;

/**
 * Filename:    ICommonRedenvelopeService.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:52:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public interface ICommonRedenvelopeService {
	/**
	 * 根据用户Id查询默认门牌下的总可用金额
	 * @param userId
	 * @return
	 */
	Long getTotalHbBalance(BigInteger userId);

	/**
	 *
	 * @param userId
	 * @param type 0全国通用，1优选体验店专用
	 * @return
	 */
	Long getTotalHbBalance(BigInteger userId, int type);

	/**
	 * 存储粮票信息
	 * @param userId 操作的用户Id
	 * @param roomId 门牌Id
	 * @param fromId 来源Id
	 * @param fromType 来源类型
	 * @param moneyLong 粮票金额
	 */
	void addPayRedEnvelope(BigInteger userId, BigInteger roomId, BigInteger fromId, Integer fromType, Long moneyLong);

	/**
	 * 粮票更新
	 * @param userId 用户id
	 * @param JFBAmount 使用粮票金额
	 * @param orderId 订单id
     * @return
     */
	Integer updateRedenvelopeByUserId(BigInteger userId, long JFBAmount, BigInteger orderId);

	/**
	 * 更新订单金额 + 优惠金额
	 * @param userId
	 * @param ebuyOrder
	 * @param jfbAmt 粮票金额
	 * @param preferAmt 随机立减金额
     * @return
     */
	void updateOrderEntityByJFB(EbuyOrder ebuyOrder, Long jfbAmt, Long preferAmt);

	/**
	 * 更新优惠表
	 * @param ebuyOrder
	 * @param jfbAmt
     */
	void updatePayCouponByJFB(EbuyOrder ebuyOrder, Long jfbAmt);
}
