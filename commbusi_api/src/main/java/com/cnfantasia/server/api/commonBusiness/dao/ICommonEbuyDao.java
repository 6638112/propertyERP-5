/**   
* Filename:    ICommonEbuyDao.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:38:21   
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

import com.cnfantasia.server.api.commonBusiness.entity.PayCouponEntity;
import com.cnfantasia.server.api.commonBusiness.entity.PayRedEnvelopeBackMoneyEntity;
import com.cnfantasia.server.business.pub.entity.Order4HJX;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;

/**
 * Filename:    ICommonEbuyDao.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:38:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public interface ICommonEbuyDao {
	
	/**
	 * 查询订单的优惠信息
	 * @param orderId 订单Id
	 * @return
	 */
	List<PayCouponEntity> selectPayCouponListByOrderId(BigInteger orderId);
	
	/**
	 * 批量退回粮票
	 * @param payRedEnvelopeBackMoneyList 粮票Id+待退回的金额
	 * @return
	 */
	int backMoney2HbBatch(List<PayRedEnvelopeBackMoneyEntity> payRedEnvelopeBackMoneyList);
	
	/**
	 * 查询用户的购物车商品列表
	 * @param userId
	 * @param userType
	 * @return
	 */
	List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductList(BigInteger userId, Integer userType);
	
	/**
	 * 转移购物车信息
	 */
	Integer convertEbuyBuyCarProdcts(BigInteger fromUser, Integer fromType, BigInteger toUser, Integer toType);
	
	/**
	 * 准备待推送的订单
	 * 
	 * @param orderId
	 * @param splMerchatName
	 * @return
	 */
	Order4HJX preparOrder4SplMerchat(BigInteger orderId, BigInteger splMerchantId);
}
