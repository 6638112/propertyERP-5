package com.cnfantasia.server.ms.ebuyProductSettle.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.*;

/**
 * 购销供应商结算dao
 * 
 * @author liyulin
 * @version 1.0 2016年6月7日 下午4:50:25
 */
public interface IEbuyProductSettleDao {
	
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
	 * 结算申请弹出框信息
	 * @param paramMap
	 * @return
	 */
	public SettleApplyDialogInfoDto selectRevenueApplyInfoForDialog(Map<String, Object> paramMap);
	
	/**
	 * 逻辑删除申请失败的t_revenue_apply记录
	 * @param paramMap
	 * @return
	 */
	public List<RevenueApply> selectRevenueApplyForDelete(Map<String, Object> paramMap);
	
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
	 * 结算申请各种金额查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<RevenueAllTotalAmount> selectAllTotalAmount(Map<String, Object> paramMap);
	
	/**
	 * 查询申请页面用户实际支付的金额
	 * 
	 * @param paramMap
	 * @return
	 */
	public BigDecimal selectRealPayTotalAmount(Map<String, Object> paramMap);
	
	/**
	 * 查询结算申请页面导出列表
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<EbuyProductSettleApplyListExportDto> selectForexportRevenueApplyList(Map<String, Object> paramMap);
	
	/**
	 * 查询购销供应商id
	 * 
	 * @param account
	 * @return
	 */
	public List<EbuySupplyMerchant> selectMerchantsWithPurchase(String account);
	
	/**
	 * 结算管理页面数据导出查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<EbuyProductSettleAdminExportDto> selectRevenueAdminExportList(Map<String, Object> paramMap);
	
	/**
	 * 管理员结算详情导出
	 * 
	 * @param revenueApplyId
	 * @return
	 */
	public List<EbuyProductSettleListDetailAdminDto> selectRevenueDetailAdminExport(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType);

	public List<EbuyProductSettleApplyExportDto> getEbuyProductSettleApplyList(Map<String, Object> paramMap);

	public List<EbuyProductSettleDetailAdminDto> getEbuyProductSettleDetailExport(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType);
}
