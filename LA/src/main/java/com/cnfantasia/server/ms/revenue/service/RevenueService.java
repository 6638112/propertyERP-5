package com.cnfantasia.server.ms.revenue.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.payBill.service.PayBillBaseService;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;

public class RevenueService extends PayBillBaseService implements IRevenueService {
	private IRevenueDao revenueDao;

	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}

	@Override
	public List<Map<String, Object>> select_revenueList(Map<String, Object> paramMap) {
		return revenueDao.select_revenueList(paramMap);
	}

	@Override
	public int select_revenueList_forCount(Map<String, Object> paramMap) {
		return revenueDao.select_revenueList_forCount(paramMap);
	}
}
