package com.cnfantasia.server.ms.cashFlowSummary.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.cnfantasia.server.ms.cashFlowSummary.entity.CashFlowSummaryDto;

/**
 * 现金流量汇总
 * 
 * @author liyulin
 * @version 1.0 2016年7月15日 下午2:27:54
 */
public interface ICashFlowSummaryService {

	/**
	 * 查询现金流量汇总记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	public int selectCashFlowSummaryForCount(Map<String, Object> paramMap);
	
	/**
	 * 查询现金流量汇总
	 * 
	 * @param paramMap
	 * @return List<CashFlowSummaryDto>
	 */
	public List<CashFlowSummaryDto> selectCashFlowSummaryForList(Map<String, Object> paramMap);
	
	/**
	 * 查询现金流量汇总的总金额
	 * 
	 * @param paramMap
	 * @return
	 */
	public BigDecimal selectCashFlowSummaryForTotalAmount(Map<String, Object> paramMap);
	
	/**
	 * 导出现金流量汇总
	 * 
	 * @param paramMap
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook exportCashFlowSummary(Map<String, Object> paramMap);
}
