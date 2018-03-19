package com.cnfantasia.server.api.roomVerifyPayment.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.roomVerifyPayment.dao.RoomVerifyPaymentBaseDao;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;

/**
 * 门牌验证缴费情况
 * 
 * @author liyulin
 * @version 1.0 2016年7月15日 上午10:59:50
 */
public class RoomVerifyPaymentDao extends RoomVerifyPaymentBaseDao implements IRoomVerifyPaymentDao {
	
	/**
	 * 根据buildingId查询RoomVerifyPayment相关信息（id、tRealRoomId为空）
	 * 
	 * @param buildingId
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> selectRoomVerifyPaymentsByBuildingId(BigInteger buildingId){
		return sqlSession.selectList("roomVerifyPayment.select_roomVerifyPayment", buildingId);
	}

	/**
	 * 查询暂时还从未缴费的门牌
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> selectRoomVerifyPaymentsWithNoPay(Map<String, Object> paramMap){
		return sqlSession.selectList("roomVerifyPayment.select_roomVerifyPayment_withNoPay", paramMap);
	}
}
