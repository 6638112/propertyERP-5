package com.cnfantasia.server.api.carMsgTask.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.carHuaanMsg.entity.CarHuaanMsg;

public class CarHADao extends AbstractBaseDao {
	
	/**
	 * 根据条件查询(华安临停车缴费通知记录)信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CarHuaanMsg> selectCarHuaanMsgByCondition(Map<String, Object> paramMap) {
		return sqlSession.selectList("carHuaanMsg.select_carHuaanMsg", paramMap);
	}
	
}
