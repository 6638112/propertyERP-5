package com.cnfantasia.server.api.ebuy.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;

public interface IEbuyFilmTicketService {
	
	public void processTicketForPreSubmit(Long orderId);
	
	public void processTicketForSubmitOrder(Long orderId);
	
	public Integer updateFilmTicketLockToUsed(Long orderId);
	
	public List<EbuyFilmTicket> getFilmTickList(Map<String, Object> paramMap);
	
//	public Integer updateFilmTicketLockReset(Long orderId);
	
//	public List<EbuyFilmTicket> fetchFilmTickByOrderId(Long orderId);
//	
//	public Integer updateFilmTicketToLock(EbuyFilmTicket ticket);
//	
//	
//	public Integer updateFilmTicketToUsed(EbuyFilmTicket ticket);

}
