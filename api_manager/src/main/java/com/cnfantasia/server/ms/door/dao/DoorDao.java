package com.cnfantasia.server.ms.door.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.roomVerifyPayment.dao.RoomVerifyPaymentDao;
import com.cnfantasia.server.ms.door.entity.DoorVerifyAndPaymentDto;

/**
 * 门牌验证缴费
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午10:58:39
 */
public class DoorDao extends RoomVerifyPaymentDao implements IDoorDao {
	
	/**
	 * 查询门牌验证缴费情况记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	@Override
	public int selectDoorVerifyAndPaymentForCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("roomVerifyPayment.select_doorVerifyAndPayment_count", paramMap);
	}

	/**
	 * 查询门牌验证缴费情况
	 * 
	 * @param paramMap
	 * @return List<DoorVerifyAndPaymentDto>
	 */
	@Override
	public List<DoorVerifyAndPaymentDto> selectDoorVerifyAndPaymentForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("roomVerifyPayment.select_doorVerifyAndPayment_withPage", paramMap);
	}

}
