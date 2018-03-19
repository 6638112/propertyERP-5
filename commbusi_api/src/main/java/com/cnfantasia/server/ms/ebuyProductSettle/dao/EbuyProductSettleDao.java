package com.cnfantasia.server.ms.ebuyProductSettle.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleAdminExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyListDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyListExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleDetailAdminDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleListDetailAdminDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueAllTotalAmount;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueApplyDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.SettleApplyDialogInfoDto;

/**
 * 购销供应商结算dao
 * 
 * @author liyulin
 * @version 1.0 2016年6月7日 下午4:51:08
 */
public class EbuyProductSettleDao extends AbstractBaseDao implements IEbuyProductSettleDao {
	
	/**
	 * 查询结算记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<RevenueApplyDto> selectRevenueForList(Map<String, Object> paramMap){
		return sqlSession.selectList("ebuyProductSettle.select_revenueListIndex_withPage", paramMap);
	}
	
	/**
	 * 查询结算记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectRevenueForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("ebuyProductSettle.select_revenueListIndex_count", paramMap);
	}
	
	/**
	 * 结算申请弹出框信息
	 * @param paramMap
	 * @return
	 */
	@Override
	public SettleApplyDialogInfoDto selectRevenueApplyInfoForDialog(Map<String, Object> paramMap){
		return sqlSession.selectOne("ebuyProductSettle.select_revenueApplyInfoForDialog", paramMap);
	}
	
	/**
	 * 逻辑删除申请失败的t_revenue_apply记录
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<RevenueApply> selectRevenueApplyForDelete(Map<String, Object> paramMap){
		paramMap.put("orderNo", null);
		paramMap.put("payTimeStart", null);
		paramMap.put("payTimeEnd", null);
		return sqlSession.selectList("ebuyProductSettle.select_revenueApply_delete", paramMap);
	}
	
	/**
	 * 查询申请页面列表数据
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyListDto> selectRevenueApplyForList(Map<String, Object> paramMap, int revenueType){
		// XXX:有空优化sql
		if (revenueType == 1) {
			return sqlSession.selectList("ebuyProductSettle.select_revenueApplyListIndex_withPage", paramMap);
		} else {
			return sqlSession.selectList("ebuyProductSettle.select_revenueApplyListIndex_daixiao_withPage", paramMap);
		}
	}
	
	/**
	 * 查询申请页面数据条数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectRevenueApplyForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("ebuyProductSettle.select_revenueApplyListIndex_count", paramMap);
	}
	
	/**
	 * 查询申请页面可结算金额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public BigDecimal selectRevenueApplyForTotalAmount(Map<String, Object> paramMap, Integer revenueType){
		paramMap.put("orderNo", null);
		paramMap.put("payTimeStart", null);
		paramMap.put("payTimeEnd", null);
		if (revenueType != null && revenueType.compareTo(1) == 0) {
			return sqlSession.selectOne("ebuyProductSettle.select_revenueApplyListIndex_totalAmount", paramMap);
		} else {
			return sqlSession.selectOne("ebuyProductSettle.select_revenueApplyListIndex_daixiao_totalAmount", paramMap);
		}
	}
	
	/**
	 * 结算申请各种金额查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<RevenueAllTotalAmount> selectAllTotalAmount(Map<String, Object> paramMap){
		paramMap.put("orderNo", null);
		paramMap.put("payTimeStart", null);
		paramMap.put("payTimeEnd", null);
		return sqlSession.selectList("ebuyProductSettle.select_all_totalAmount", paramMap);
	}
	
	/**
	 * 查询申请页面用户实际支付的金额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	@Deprecated
	public BigDecimal selectRealPayTotalAmount(Map<String, Object> paramMap){
		paramMap.put("orderNo", null);
		paramMap.put("payTimeStart", null);
		paramMap.put("payTimeEnd", null);
		return sqlSession.selectOne("ebuyProductSettle.select_realPay_totalAmount", paramMap);
	}
	
	/**
	 * 查询结算申请页面导出列表
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyListExportDto> selectForexportRevenueApplyList(Map<String, Object> paramMap){
		return sqlSession.selectList("ebuyProductSettle.select_revenueApplyListIndex_export", paramMap);
	}
	
	/**
	 * 查询购销供应商id
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> selectMerchantsWithPurchase(String account){
		return sqlSession.selectList("ebuyProductSettle.select_merchants_with_purchase", account);
	}
	
	/**
	 * 结算管理页面数据导出查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<EbuyProductSettleAdminExportDto> selectRevenueAdminExportList(Map<String, Object> paramMap){
		return sqlSession.selectList("ebuyProductSettle.select_revenueAdmin_export", paramMap);
	}
	
	/**
	 * 管理员结算详情导出
	 * 
	 * @param revenueApplyId
	 * @return
	 */
	@Override
	public List<EbuyProductSettleListDetailAdminDto> selectRevenueDetailAdminExport(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("revenueApplyId", revenueApplyId);
		paramMap.put("revApplyFinanceId", revApplyFinanceId);
		paramMap.put("visibleType", visibleType);
		return sqlSession.selectList("ebuyProductSettle.select_revenueDetailAdmin_export", paramMap);
	}

	@Override
	public List<EbuyProductSettleApplyExportDto> getEbuyProductSettleApplyList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyProductSettle.select_revenueApplyListIndex_daixiao_export", paramMap);
	}

	@Override
	public List<EbuyProductSettleDetailAdminDto> getEbuyProductSettleDetailExport(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("revenueApplyId", revenueApplyId);
		paramMap.put("revApplyFinanceId", revApplyFinanceId);
		paramMap.put("visibleType", visibleType);
		return sqlSession.selectList("ebuyProductSettle.select_revenueDetailAdmin_daixiao_export", paramMap);
	}
	
}
