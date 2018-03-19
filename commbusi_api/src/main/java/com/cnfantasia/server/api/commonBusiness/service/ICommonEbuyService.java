/**   
* Filename:    ICommonEbuyService.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:37:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.entity.PayCouponEntity;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * Filename:    ICommonEbuyService.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:37:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public interface ICommonEbuyService {
	/**
	 * 根据粮票金额，更新订单,返回订单应付金额
	 * 注：粮票金额应小于或等于订单金额
	 * @param userId 用户id
	 * @param orderId 订单Id
	 * @param hbMoney 要使用的粮票金额
	 */
	Long updateOrderByHb(BigInteger userId, BigInteger orderId, Long hbMoney);
	
	/**
	 * 根据优惠券更新订单信息,返回订单应付金额
	 * @param userId 用户id
	 * @param orderId 订单Id
	 * @param couponIdList 要使用的优惠列表
	 * @return
	 */
	Long updateOrderByCopounList(BigInteger userId, BigInteger orderId, Set<BigInteger> couponIdList, Integer jfqSubType, Map<BigInteger, Long> merchantFeeMap);
	
//	/**
//	 * 根据积分金额，对订单进行支付
//	 * @param userId 用户id
//	 * @param orderId 订单Id
//	 * @param pointValue 要使用的积分金额
//	 * @return
//	 */
//	public Long updateOrderByPoint(BigInteger userId,BigInteger orderId,Long pointValue);
	
	/**
	 * 退回粮票
	 * @param userId
	 * @param orderId
	 * return 退回的粮票金额
	 */
	Long backHbByOrderId(BigInteger userId, BigInteger orderId);
	
	/**
	 * 查询订单的优惠信息
	 * @param orderId 订单Id
	 * @return
	 */
	List<PayCouponEntity> getPayCouponListByOrderId(BigInteger orderId);
	
	/**
	 * 订单支付成功相关处理
	 * 	更改订单状态为已支付
	 * 	增加支付成功记录
	 * 	设置配送单信息
	 * @param orderId 订单Id
	 * @param payMethod 支付方式
	 */
	void paySuccessOperateComm(BigInteger orderId, Integer payMethod);

	/**
	 * 根据订单编号查询订单基本信息
	 * @param orderNo
	 * @return
	 */
	EbuyOrder selectEbuyOrderByOrderNo(String orderNo);

	/**
	 * 转移购物车商品信息
	 * @param fromUser
	 * @param fromType
	 * @param toUser
	 * @param toType
	 */
	void collectEbuyBuyCar(BigInteger fromUser, Integer fromType, BigInteger toUser, Integer toType);
	
	/**
	 * 检查用户是否拥有购物车，若不存在则创建一个购物车
	 * @param userId
	 * @param userType
	 * @return 返回已存在的或者新创建的购物车Id
	 */
	BigInteger checkAndCreateEbuyBuyCar(BigInteger userId, Integer userType);

	/**
	 * 推送订单到供应商（海吉星）
	 * 
	 * @param orderId
	 *            订单ID
	 * @param splMerchantId
	 *            电商ID
	 */
	void pushOrder2SplMerchat(BigInteger orderId, BigInteger splMerchantId);

	/**
	 * 粮票支付 2016-06-16
	 * @param userId
	 * @param ebuyOrderAddId
	 * @param hbMoney
	 * @return
	 */
	Long updateOrderByHb02(BigInteger userId, BigInteger ebuyOrderAddId, Long hbMoney);
}
