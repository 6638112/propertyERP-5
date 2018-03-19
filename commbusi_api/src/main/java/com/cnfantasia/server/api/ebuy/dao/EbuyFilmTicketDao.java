package com.cnfantasia.server.api.ebuy.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class EbuyFilmTicketDao extends AbstractBaseDao implements IEbuyFilmTicketDao{

	@Override
	public List<EbuyFilmTicket> fetchFilmTickByOrderId(Long orderId) {
		return sqlSession.selectList("ebuyFilmTicket.fetchFilmTickByOrderId", orderId);
	}

	@Override
	public Integer updateFilmTicketToLock(EbuyFilmTicket ticket) {
		return sqlSession.update("ebuyFilmTicket.updateFilmTicketToLock", ticket);
	}

	@Override
	public Integer updateFilmTicketLockToUsed(Long orderId) {
		return sqlSession.update("ebuyFilmTicket.updateFilmTicketLockToUsed", orderId);
	}
	
	@Override
	public Integer updateFilmTicketLockReset(Long orderId) {
		return sqlSession.update("ebuyFilmTicket.updateFilmTicketLockReset", orderId);
	}

	@Override
	public Integer updateFilmTicketToUsed(EbuyFilmTicket ticket) {
		return sqlSession.update("ebuyFilmTicket.updateFilmTicketToUsed", ticket);
	}

	@Override
	public List<EbuyFilmTicket> getFilmTickList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyFilmTicket.getFilmTickList", paramMap);
	}

}
