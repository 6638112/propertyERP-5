package com.cnfantasia.server.ms.copyOrder.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.copyOrder.entity.DeliveryOrderEntity;

public class CopyOrderDao extends AbstractBaseDao{
	
	/**
	 * 查询复制的订单信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<DeliveryOrderEntity> selectDeliveryOrderForCopy(Map<String, Object> paramMap){
		return sqlSession.selectList("copyOrder.selectDeliveryOrderForCopy", paramMap);
	}
}
