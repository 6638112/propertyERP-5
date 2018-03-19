package com.cnfantasia.server.api.ebuy.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;

public interface IEbuyFilmTicketDao {
	
	public List<EbuyFilmTicket> fetchFilmTickByOrderId(Long orderId);
	
	public Integer updateFilmTicketToLock(EbuyFilmTicket ticket);
	
	public Integer updateFilmTicketLockToUsed(Long orderId);
	
	public Integer updateFilmTicketToUsed(EbuyFilmTicket ticket);
	
	public List<EbuyFilmTicket> getFilmTickList(Map<String, Object> paramMap);

	public Integer updateFilmTicketLockReset(Long orderId);

}
