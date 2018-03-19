package com.cnfantasia.server.ms.cashSqpayBt.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.api.cashSqpayBt.service.ICashSqpayBtService;
import com.cnfantasia.server.ms.cashSqpayBt.entity.CashSqpayBtDto;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:43:00
 */
public interface ICashSqpayBtOOSService extends ICashSqpayBtService{

	/**
	 * 查询双乾支付优惠补贴记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	public Integer selectCashSqpayBtCount(Map<String, Object> paramMap);
	
	/**
	 * 查询双乾支付优惠补贴记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CashSqpayBtDto> selectCashSqpayBtList(Map<String, Object> paramMap);
	
	/**
	 * 导出双乾支付优惠补贴记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public HSSFWorkbook exportCashSqpayBts(Map<String, Object> paramMap);
	
	/**
	 * 查询双乾支付优惠总补贴额
	 * 
	 * @param paramMap
	 * @return
	 */
	public Long selectCashSqpayBtForTotalAmountBt(Map<String, Object> paramMap);
}
