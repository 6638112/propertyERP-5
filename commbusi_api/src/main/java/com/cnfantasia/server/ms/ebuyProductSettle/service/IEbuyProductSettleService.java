package com.cnfantasia.server.ms.ebuyProductSettle.service;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 购销供应商结算service
 * 
 * @author liyulin
 * @version 1.0 2016年6月7日 下午4:52:59
 */
public interface IEbuyProductSettleService {
	
	/**
	 * 查询结算记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<RevenueApplyDto> selectRevenueForList(Map<String, Object> paramMap);
	
	/**
	 * 查询结算记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectRevenueForCount(Map<String, Object> paramMap);
	
	/**
	 * 查询申请页面列表数据
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<EbuyProductSettleApplyListDto> selectRevenueApplyForList(Map<String, Object> paramMap, int revenueType);
	
	/**
	 * 查询申请页面数据条数
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectRevenueApplyForCount(Map<String, Object> paramMap);
	
	/**
	 * 查询申请页面可结算金额
	 * 
	 * @param paramMap
	 * @return
	 */
	public BigDecimal selectRevenueApplyForTotalAmount(Map<String, Object> paramMap, Integer revenueType);
	
	/**
	 * 结算申请弹出框信息
	 * @param paramMap
	 * @return
	 */
	public SettleApplyDialogInfoDto selectRevenueApplyInfoForDialog(Map<String, Object> paramMap);
	
	/**
	 * 结算申请
	 * 
	 * @param userId
	 * @param supplyMerchantId
	 * @param mobile
	 * @param note
	 * @return
	 */
	public BigInteger applyRevenue(BigInteger userId, BigInteger supplyMerchantId, String mobile, String note, Integer channelSub);

	
	/**
	 * 结算申请页面列表导出
	 * 
	 * @param paramMap ==>supplyMerchantIds、orderNo、payTimeStart、payTimeEnd
	 * @return
	 */
	public HSSFWorkbook exportRevenueApplyList(Map<String, Object> paramMap);
	
	/**
	 * 结算审核
	 * 
	 * @param revenueApplyId
	 * @param auditStatus
	 * @param handlerNote
	 * @param userId
	 * @return
	 */
	public boolean revenueApplyHandler(BigInteger revenueApplyId, Integer auditStatus, String handlerNote, BigInteger userId);
	
	/**
	 * 结算审核页面导出
	 * 
	 * @param request
	 * @return
	 */
	public HSSFWorkbook exportRevenueHandlerReport(HttpServletRequest request);
	
	/**
	 * 结算管理页面列表导出
	 * 
	 * @param paramMap ==>supplyMerchantId、applyTimeStart、applyTimeEnd、settleStatus
	 * @return
	 */
	public HSSFWorkbook selectRevenueAdminExportList(Map<String, Object> paramMap);
	
	/**
	 * 查询购销供应商id
	 * 
	 * @param account
	 * @return
	 */
	public List<EbuySupplyMerchant> selectMerchantsWithPurchase(String account);
	
	/**
	 * 管理员结算详情导出
	 * 
	 * @param revenueApplyId
	 * @return
	 */
	public HSSFWorkbook selectRevenueDetailAdminExportList(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType);

	public List<EbuyProductSettleApplyExportDto> getEbuyProductSettleApplyList(Map<String, Object> paramMap);

	public List<EbuyProductSettleDetailAdminDto> getEbuyProductSettleDetailExport(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType);
}
