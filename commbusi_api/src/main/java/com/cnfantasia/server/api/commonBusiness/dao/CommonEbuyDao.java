/**   
* Filename:    CommonEbuyDao.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:38:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.PayCouponEntity;
import com.cnfantasia.server.api.commonBusiness.entity.PayRedEnvelopeBackMoneyEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.Order4HJX;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;

/**
 * Filename:    CommonEbuyDao.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:38:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public class CommonEbuyDao extends AbstractBaseDao implements ICommonEbuyDao{

	@Override
	public List<PayCouponEntity> selectPayCouponListByOrderId(BigInteger orderId) {
		return sqlSession.selectList("commonEbuy.select_PayCoupon_List_ByOrderId", orderId);
	}

	@Override
	public int backMoney2HbBatch(List<PayRedEnvelopeBackMoneyEntity> payRedEnvelopeBackMoneyList) {
		return sqlSession.update("commonEbuy.back_Money2Hb_Batch", payRedEnvelopeBackMoneyList);
	}
	
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductList(BigInteger userId, Integer userType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userType", userType);
		return sqlSession.selectList("commonEbuy.select_EbuyBuyCarHasTEbuyProduct_List",map);
	}

	@Override
	public Integer convertEbuyBuyCarProdcts(BigInteger fromUser, Integer fromType, BigInteger toUser, Integer toType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("fromUser", fromUser);
		map.put("fromType", fromType);
		map.put("toUser", toUser);
		map.put("toType", toType);
		return sqlSession.selectOne("commonEbuy.convert_EbuyBuyCar_Prodcts",map);
	}

	@Override
	public Order4HJX preparOrder4SplMerchat(BigInteger orderId, BigInteger splMerchantId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("splMerchantId", splMerchantId);
		return sqlSession.selectOne("commonEbuy.getDeliveryAndProducts_byOrderIdAndSplChantName", tmpMap);
	}
}
