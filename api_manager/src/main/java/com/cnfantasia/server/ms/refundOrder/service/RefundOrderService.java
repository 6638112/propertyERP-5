package com.cnfantasia.server.ms.refundOrder.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.cnfantasia.server.ms.refundOrder.dao.IRefundOrderDao;
import com.cnfantasia.server.ms.refundOrder.entity.RefudOrderEntity;

public class RefundOrderService implements IRefundOrderService{
	@Resource
	private IRefundOrderDao refundOrderDao;
	
	@Override
	public List<RefudOrderEntity> selectRefundlist(Map<String, Object> paramMap) {
		return refundOrderDao.selectRefundlist(paramMap);
	}

	@Override
	public int selectlistnum(Map<String, Object> paramMap) {
		return refundOrderDao.selectlistnum(paramMap);
	}

	@Override
	public RefudOrderEntity getRefunddetail(BigInteger refundId) {
		return refundOrderDao.getRefunddetail(refundId);
	}
	
}
