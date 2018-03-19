package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


import com.cnfantasia.server.api.ebuy.entity.PushEguOrderEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;

public class EguDao extends AbstractBaseDao implements IEguDao{
	@Override
	public Integer updateExpress(Map<String, Object> paramMap) {
		return sqlSession.update("egu.updateExpress", paramMap);
	}

	@Override
	public List<PushEguOrderEntity> selectPushEguOrder() {
		return sqlSession.selectList("egu.pushEguOrderList");
	}

	@Override
	public Integer updateDeliveryOrder(Map<String, Object> paramMap) {
		return sqlSession.update("egu.updateDeliveryOrder", paramMap);
	}

	@Override
	public EbuyProductTemp selectFguProduct(BigInteger prosrcId) {
		return sqlSession.selectOne("egu.select_detail_byEguPrdtId",prosrcId);
	}

	@Override
	public EbuyDeliveryOrder selectTenmimsg(BigInteger order_no) {
		return sqlSession.selectOne("egu.select_push_tenfenzhong", order_no);
	}

	@Override
	public List<EbuyDeliveryOrder> getEguDelivOrderNoExpress() {
		return sqlSession.selectList("egu.getEguDelivOrderNoExpress");
	}

	@Override
	public Integer delEguExpressByDeliverOrderId(BigInteger deliveryOrderId) {
		return sqlSession.delete("egu.delEguExpressByDeliverOrderId", deliveryOrderId);
	}

}
