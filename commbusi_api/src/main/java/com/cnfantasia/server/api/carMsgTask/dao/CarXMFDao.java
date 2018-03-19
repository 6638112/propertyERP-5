package com.cnfantasia.server.api.carMsgTask.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;

public class CarXMFDao extends AbstractBaseDao {
	
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarXmfMsg> selectCarXmfMsgByCondition(Map<String,Object> paramMap){
		return sqlSession.selectList("carXmfMsg.select_carXmfMsg",paramMap);
	}
}
