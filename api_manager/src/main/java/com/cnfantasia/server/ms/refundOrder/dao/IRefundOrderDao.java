package com.cnfantasia.server.ms.refundOrder.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.refundOrder.entity.RefudOrderEntity;


public interface IRefundOrderDao {
	public List<RefudOrderEntity> selectRefundlist(Map<String, Object> paramMap);
	public int selectlistnum(Map<String, Object> paramMap);
	public RefudOrderEntity getRefunddetail(BigInteger refundId);
}
