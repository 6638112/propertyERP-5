package com.cnfantasia.server.api.ebuyorder.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.Order4HJX;

public class EbuyOrderPusherDao extends AbstractBaseDao {

	public Order4HJX preparOrder4SplMerchat(BigInteger orderId, BigInteger splMerchantId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("splMerchantId", splMerchantId);
		return sqlSession.selectOne("commonEbuy.getDeliveryAndProducts_byOrderIdAndSplChantName", tmpMap);
	}

	/**
	 * 准备海吉星订单推送数据，查出所有当天待推送的订单
	 * 
	 * @return
	 */
	public List<Order4HJX> preparOrder4HJXSplMerchat() {
		return sqlSession.selectList("commonEbuy.getDeliveryAndProducts_forHJXOrderPusher");
	}
	
	public BigInteger selectEbuyDeliveryOrderForUpdate(BigInteger ebuyDeliveryOrderId){
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("ebuyDeliveryOrderId", ebuyDeliveryOrderId);
		return sqlSession.selectOne("commonEbuy.select_EbuyDeliveryOrder_ForUpdate", tmpMap);
	}
}
