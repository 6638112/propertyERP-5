package com.cnfantasia.server.api.ebuyorder.dao;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchant4List;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchantEntity;
import com.cnfantasia.server.api.ebuyorder.entity.*;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueApplyDto;
import org.apache.ibatis.session.ResultHandler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author yewj
 */
public class EbuyMerchantDao extends AbstractBaseDao {

	public EbuyMerchantBean getEbuyMerchant(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyMerchant2.getEbuyMerchant", paramMap);
	}

//	public List<MerchantOrder> getEbuyDeliveryOrderList(Map<String, Object> paramMap) {
//		return sqlSession.selectOne("ebuyMerchant2.getEbuyDeliveryOrderList", paramMap);
//	}

	public List<MerchantOrder> getEbuyDeliveryOrderList(Map<String, Object> paramMap, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, "ebuyMerchant2.getEbuyDeliveryOrderList", "ebuyMerchant2.getEbuyDeliveryOrderCount");
	}

	public List<MerchantProdLst> getMerchantProductList(Map<String, Object> paramMap, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, "ebuyMerchant2.getMerchantProductList", "ebuyMerchant2.getMerchantProductCount");
	}

	public List<EbuyProdType> getProdTypes(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyMerchant2.getProdTypes", paramMap);
	}

	public List<EbuyProdType> getAllProdTypes() {
		return sqlSession.selectList("ebuyMerchant2.getAllProdTypes");
	}

	public MerchantProdDetail getMerchantProdDetail(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyMerchant2.getMerchantProdDetail", paramMap);
	}

	public int deletePodParams(BigInteger prodId) {
		return sqlSession.delete("ebuyMerchant2.deletePodParams", prodId);
	}

	public MerchantEbuyProduct getMerchantProduct(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyMerchant2.getMerchantProduct", paramMap);
	}

	public EbuySupplyMerchantEntity getMerchantSupply(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyMerchant2.getMerchantSupply", paramMap);
	}

	public int updateEbuyMerchant(Map<String, Object> paramMap) {
		return sqlSession.update("ebuyMerchant2.updateEbuyMerchant", paramMap);
	}

	public int qrySupplyMerchantList_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "ebuyMerchant2.qrySupplyMerchantList", paramMap);
	}

	public List<EbuySupplyMerchant4List> qrySupplyMerchantList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyMerchant2.qrySupplyMerchantList",paramMap);
	}

	public Merchant4AuditBean getMerchat4AuditOrView(String id) {
		return sqlSession.selectOne("ebuyMerchant2.getMerchat4AuditOrView",id);
	}

	public int deleteLicenseByMerchantId(BigInteger id) {
		return sqlSession.delete("ebuyMerchant2.deleteLicenseByMerchantId",id);
	}

	public int deleteGroupBuildingByMerchantId(BigInteger id) {
		return sqlSession.delete("ebuyMerchant2.deleteGroupBuildingByMerchantId",id);
	}

	public List<DeliveryMethod> selectDeliveryMethodByMerchantId(BigInteger id) {
		return sqlSession.selectList("ebuyMerchant2.selectDeliveryMethodByMerchantId",id);
	}

	public List<DeliveryMethod> selectDeliveryMethodCondition(Map<String, Object> param) {
		return sqlSession.selectList("ebuyMerchant2.selectDeliveryMethodCondition", param);
	}

	public int deleteDeliveryMethodByMerchantId(BigInteger esmId) {
		return sqlSession.delete("ebuyMerchant2.deleteDeliveryMethodByMerchantId",esmId);
	}

	public int deleteMerchantDelivFeeSettlementByMerchantId(BigInteger merchantId) {
		return sqlSession.delete("ebuyMerchant2.deleteMerchantDelivFeeSettlementByMerchantId",merchantId);
	}

	public List<MerchantListDto> getEbuySupplyMerchantType1(Map<String, Object> param) {
		return sqlSession.selectList("ebuyMerchant2.getEbuySupplyMerchantType1",param);
	}

	public Long getEbuySupplyMerchantType1Count(Map<String, Object> param) {
		return sqlSession.selectOne("ebuyMerchant2.getEbuySupplyMerchantType1Count",param);
	}

	public List<Map<String, Object>> getSupplyMerchantType1CityList(BigInteger supplyMerchantId) {
		return sqlSession.selectList("ebuyMerchant2.getSupplyMerchantType1CityList",supplyMerchantId);
	}

	public SupplyMerchantDeliveryFeeSettlement getMerchantSettleFeeByAmountAndMerchantId(BigInteger merchantId, Long totalFee) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("merchantId", merchantId);
		param.put("totalFee", totalFee);
		return sqlSession.selectOne("ebuyMerchant2.getMerchantSettleFeeByAmountAndMerchantId",param);
	}

	/**
	 * 查询供应商运费信息
	 *
	 * @param merchantId
	 * @return
	 */
	public List<MerchantFeeDto> getMerchantFeeList(BigInteger merchantId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("merchantId", merchantId);
		return sqlSession.selectList("ebuyMerchant2.getMerchantFeeList",param);
	}

	/**
	 * 更新货架价格
	 * @param productId 商品ID
	 * @param productTypeId 商品类别
	 * @param price 市场价
	 * @param priceDiscount 销售价
     * @return 更新条数
     */
	public Integer updateShelfPriceByProductId(BigInteger productId, BigInteger productTypeId, Long price, Long priceDiscount) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productId", productId);
		param.put("productTypeId", productTypeId);
		param.put("price", price);
		param.put("priceDiscount", priceDiscount);
		return sqlSession.update("ebuyMerchant2.updateShelfPriceByProductId", param);
	}
	
	public Integer updateShelfPriceByProductId(Map<String, Object> paramMap) {
		return sqlSession.update("ebuyMerchant2.updateShelfPriceByProductId", paramMap);
	}

	public List<SettleDelivOrder> getOrderNotApplySettle(Map<String, Object> param) {
		return sqlSession.selectList("ebuyMerchant2.getOrderNotApplySettle", param);
	}

	public DeliveryOrderDetailEntity getDeliveryOrderDetail(BigInteger deliveryOrderId, BigInteger supplyMerchantId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deliveryOrderId", deliveryOrderId);
		param.put("supplyMerchantId", supplyMerchantId);
		return sqlSession.selectOne("ebuyMerchant2.getDeliveryOrderDetail", param);
	}

	public List<RevenueApplyDto> getRevenueApplyList(BigInteger supplyMerchantId, Integer settleStatusType, PageModel pageModel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplyMerchantId", supplyMerchantId);
		param.put("settleStatusType", settleStatusType);
		if (pageModel != null) {
			param.put("_begin", pageModel.getBegin());
			param.put("_length", pageModel.getLength());
		}
		return sqlSession.selectList("ebuyMerchant2.getRevenueApplyList", param);
	}

	public Integer getRevenueApplyListCount(BigInteger supplyMerchantId, Integer settleStatusType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplyMerchantId", supplyMerchantId);
		param.put("settleStatusType", settleStatusType);
		return sqlSession.selectOne("ebuyMerchant2.getRevenueApplyListCount", param);
	}

	public List<DeliveryOrderDetailEntity> getSettleDeliveryOrderList(BigInteger supplyMerchantId, BigInteger applyId, PageModel pageModel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplyMerchantId", supplyMerchantId);
		param.put("revenueApplyId", applyId);
		if (pageModel != null) {
			param.put("_begin", pageModel.getBegin());
			param.put("_length", pageModel.getLength());
		}
		return sqlSession.selectList("ebuyMerchant2.getSettleDeliveryOrderList", param);
	}

	public List<CommUserDataEntity> getUserDataInMerchantService(BigInteger merchantId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("merchantId", merchantId);
		return sqlSession.selectList("ebuyMerchant2.getUserDataInMerchantService", param);
	}

	public int updateEbuySupplyMerchantShopPic(EbuySupplyMerchant merchant) {
		return sqlSession.update("ebuyMerchant2.updateEbuySupplyMerchantShopPic", merchant);
	}

	/**
	 * 排序置顶
	 * @param prdtId
	 * @return
	 */
	public int updateSortToTop(BigInteger prdtId) {
		return sqlSession.update("ebuyMerchant2.updateSortToTop", prdtId);
	}

	public Map<String, Object> getCarCouponNums(Long supplyId) {
		return sqlSession.selectOne("ebuyMerchant2.getCarCouponNums", supplyId);
	}
}
