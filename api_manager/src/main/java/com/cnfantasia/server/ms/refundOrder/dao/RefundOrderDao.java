package com.cnfantasia.server.ms.refundOrder.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.ms.refundOrder.entity.RefudOrderEntity;
public class RefundOrderDao implements IRefundOrderDao{
	
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<RefudOrderEntity> selectRefundlist(Map<String, Object> paramMap) {
		return sqlSession.selectList("refundOrder.refundOrderListall",paramMap);
	}

	@Override
	public int selectlistnum(Map<String, Object> paramMap) {
		return sqlSession.selectOne("refundOrder.refundOrderListcount", paramMap);
	}

	@Override
	public RefudOrderEntity getRefunddetail(BigInteger refundId) {
		return sqlSession.selectOne("refundOrder.refundOrderDetail", refundId);
	}
	
}
