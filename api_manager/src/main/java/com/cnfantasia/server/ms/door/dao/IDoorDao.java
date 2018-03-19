package com.cnfantasia.server.ms.door.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.ms.door.entity.DoorVerifyAndPaymentDto;

/**
 * 门牌验证缴费
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午10:58:39
 */
public interface IDoorDao extends IRoomVerifyPaymentDao{
	
	/**
	 * 查询门牌验证缴费情况记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	public int selectDoorVerifyAndPaymentForCount(Map<String, Object> paramMap);

	/**
	 * 查询门牌验证缴费情况
	 * 
	 * @param paramMap
	 * @return List<DoorVerifyAndPaymentDto>
	 */
	public List<DoorVerifyAndPaymentDto> selectDoorVerifyAndPaymentForList(Map<String, Object> paramMap);
}
