package com.cnfantasia.server.ms.copyOrder.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.copyOrder.dao.CopyOrderDao;
import com.cnfantasia.server.ms.copyOrder.entity.DeliveryOrderEntity;

public class CopyOrderService {
	
	private CopyOrderDao copyOrderDao;

	public void setCopyOrderDao(CopyOrderDao copyOrderDao) {
		this.copyOrderDao = copyOrderDao;
	}

	/**
	 * 查询复制的订单信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<DeliveryOrderEntity> getDeliveryOrderForCopy(Map<String, Object> paramMap) {
		return copyOrderDao.selectDeliveryOrderForCopy(paramMap);
	}
}
