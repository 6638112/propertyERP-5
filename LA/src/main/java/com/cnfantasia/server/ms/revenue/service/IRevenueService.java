package com.cnfantasia.server.ms.revenue.service;

import java.util.List;
import java.util.Map;

public interface IRevenueService {
	public List<Map<String, Object>> select_revenueList(Map<String, Object> paramMap);

	public int select_revenueList_forCount(Map<String, Object> paramMap);
}
