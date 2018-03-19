package com.cnfantasia.server.ms.refundOrder.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.refundOrder.entity.RefudOrderEntity;

public interface IRefundOrderService {
	public List<RefudOrderEntity> selectRefundlist(Map<String, Object> paramMap);
	public int selectlistnum(Map<String, Object> paramMap);
	public RefudOrderEntity getRefunddetail(BigInteger refundId);
}
