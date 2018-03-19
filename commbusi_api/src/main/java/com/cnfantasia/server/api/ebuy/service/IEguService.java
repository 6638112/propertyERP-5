package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.PushEguOrderEntity;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;

public interface IEguService {
	public Integer updateExpress(Map<String, Object> paramMap);
	public EbuyProductTempEntity getProductTemp(String srcId);
	public List<PushEguOrderEntity> updatePushEguOrder();
	public Integer updateDeliveryOrder(Map<String, Object> paramMap);
	public void updateORinsertproduct(EbuyProductTempEntity ebuyProductTempEntity,String piclist) throws Exception;
	public EbuyDeliveryOrder selectTenmimsg(BigInteger order_no);

	/**
	 * 获取未同步物流的依谷订单
	 * @return
     */
	public List<EbuyDeliveryOrder> getEguDelivOrderNoExpress();

	public Integer delEguExpressByDeliverOrderId(BigInteger deliveryOrderId);
}
