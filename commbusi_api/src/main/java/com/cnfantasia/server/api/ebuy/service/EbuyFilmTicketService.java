package com.cnfantasia.server.api.ebuy.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.ebuy.dao.IEbuyFilmTicketDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

public class EbuyFilmTicketService implements IEbuyFilmTicketService {
	
	private IEbuyFilmTicketDao ebuyFilmTicketDao;
	
	private ICommonLockDao commonLockDao;
	
	//电影票处理，预支付时锁定购买的电影票。30分钟内未付款成功可以被其它人购买。
	/**
	 * 预支付时锁定电影券
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void processTicketForPreSubmit(Long orderId) {
		List<EbuyFilmTicket> ticketList = fetchFilmTickByOrderId(orderId);
		//解决一个订单第一次预付款后并未支付，再次进行预付款并支付时。重置之前锁定的，防止多分配密码券给用户
		ebuyFilmTicketDao.updateFilmTicketLockReset(orderId);
		
		commonLockDao.getLock(Lock.EBUY_ORDER_FILE_TICKET); //防止批量更新的死锁问题
		for(EbuyFilmTicket ticket : ticketList) {
			Integer updateNum = updateFilmTicketToLock(ticket);
			if(!ticket.getTicketNum().equals(updateNum)) {
				throw new BusinessRuntimeException("EbuyPayService.prepayRequest.fileTicket.isSellOut");
			}
		}
	}
	
	/**
	 * 粮票等购买不需要现金支付时，直接在提交订单时就使用电影券，不需要再锁定操作。
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void processTicketForSubmitOrder(Long orderId) {
		List<EbuyFilmTicket> ticketList = fetchFilmTickByOrderId(orderId);
		
		commonLockDao.getLock(Lock.EBUY_ORDER_FILE_TICKET);
		for(EbuyFilmTicket ticket : ticketList) {
			Integer updateNum = updateFilmTicketToUsed(ticket);
			if(!ticket.getTicketNum().equals(updateNum)) {
				throw new BusinessRuntimeException("EbuyPayService.prepayRequest.fileTicket.isSellOut");
			}
		}
	}
	
	/**
	 * 解决一个订单第一次预付款后并未支付，再次进行预付款并支付时。重置之前锁定的，防止多分配密码券给用户
	 */
//	private Integer updateFilmTicketLockReset(Long orderId) {
//		return ebuyFilmTicketDao.updateFilmTicketLockReset(orderId);
//	}
	
	/**
	 * 支付成功回调时，把相应的锁定的电影券设置成使用状态
	 */
	@Override
	public Integer updateFilmTicketLockToUsed(Long orderId) {
		return ebuyFilmTicketDao.updateFilmTicketLockToUsed(orderId);
	}
	
	/**
	 * 订单详情时，返回相应的电影券密码
	 */
	@Override
	public List<EbuyFilmTicket> getFilmTickList(Map<String, Object> paramMap) {
		return ebuyFilmTicketDao.getFilmTickList(paramMap);
	}

	private List<EbuyFilmTicket> fetchFilmTickByOrderId(Long orderId) {
		return ebuyFilmTicketDao.fetchFilmTickByOrderId(orderId);
	}

	private Integer updateFilmTicketToLock(EbuyFilmTicket ticket) {
		return ebuyFilmTicketDao.updateFilmTicketToLock(ticket);
	}

	private Integer updateFilmTicketToUsed(EbuyFilmTicket ticket) {
		return ebuyFilmTicketDao.updateFilmTicketToUsed(ticket);
	}

	public void setEbuyFilmTicketDao(IEbuyFilmTicketDao ebuyFilmTicketDao) {
		this.ebuyFilmTicketDao = ebuyFilmTicketDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

}
