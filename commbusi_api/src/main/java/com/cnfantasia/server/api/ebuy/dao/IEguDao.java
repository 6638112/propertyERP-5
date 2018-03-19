package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.PushEguOrderEntity;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;

public interface IEguDao {
	public Integer updateExpress(Map<String, Object> paramMap);
	//依谷网推送的订单
	public List<PushEguOrderEntity> selectPushEguOrder();
	public Integer updateDeliveryOrder(Map<String, Object> paramMap);
	//判断是否已经有该商品
	public EbuyProductTemp selectFguProduct(BigInteger prosrcId);
	public EbuyDeliveryOrder selectTenmimsg(BigInteger order_no);
	public List<EbuyDeliveryOrder> getEguDelivOrderNoExpress();
	public Integer delEguExpressByDeliverOrderId(BigInteger deliveryOrderId);
}
