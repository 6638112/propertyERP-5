package com.cnfantasia.server.api.ebuyorder.service;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.ebuy.constant.EbuySupplyMerchantConstant;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchant4List;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchantEntity;
import com.cnfantasia.server.api.ebuyorder.dao.EbuyMerchantDao;
import com.cnfantasia.server.api.ebuyorder.entity.DeliveryMethod;
import com.cnfantasia.server.api.ebuyorder.entity.DeliveryOrderDetailEntity;
import com.cnfantasia.server.api.ebuyorder.entity.EbuyMerchantBean;
import com.cnfantasia.server.api.ebuyorder.entity.EbuySupplyMerchantDto;
import com.cnfantasia.server.api.ebuyorder.entity.Merchant4AuditBean;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantEbuyProduct;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantFeeDto;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantListDto;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantOrder;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantProdDetail;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantProdLst;
import com.cnfantasia.server.api.ebuyorder.entity.SettleDelivOrder;
import com.cnfantasia.server.api.ebuyorder.entity.ShopInfo;
import com.cnfantasia.server.api.msgpush.constant.MsgToAddBasic;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IPushAddService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.api.pub.springSecurity.EncodeImpl;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.pub.utils.EbuyMerchantUtil;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.user.constant.UserDict;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.dao.EbuyDeliveryMethodBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service.IEbuyProductIntroducePicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductParameters.service.IEbuyProductParametersBaseService;
import com.cnfantasia.server.domainbase.ebuyProductPic.service.IEbuyProductPicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.dao.EbuySupplyMerchantBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.service.IEbuySupplyMerchantBankAccountBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.dao.EbuySupplyMerchantHasGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity.EbuySupplyMerchantHasGroupBuilding;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.dao.EbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.dao.EbuySupplyMerchantHasUserBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.entity.EbuySupplyMerchantHasUser;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.dao.EbuySupplyMerchantLicenceBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.entity.EbuySupplyMerchantLicence;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsPermiRole.service.IOmsPermiRoleBaseService;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
import com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.entity.OmsUserHasTEbuySupplyMerchant;
import com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.service.IOmsUserHasTEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service.IOmsUserHasTOmsPermiRoleBaseService;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;
import com.cnfantasia.server.domainbase.redpointContent.service.IRedpointContentBaseService;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.service.ISupplyMerchantDeliveryFeeSettlementBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueApplyDto;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;



/** 类说明：
 *
 * @author yewj */
public class EbuyMerchantService {

	private EbuyMerchantDao ebuyMerchantDao;

	private IEbuyProductBaseService ebuyProductBaseService;

	private IEbuyProductPicBaseService ebuyProductPicBaseService;

	private IEbuyProductParametersBaseService ebuyProductParametersBaseService;

	private IEbuyProductShelfBaseService ebuyProductShelfBaseService;

    @Resource
    private IPushAddService pushAddService;

    @Resource
	private IUuidManager uuidManager;
	@Resource
	private IEbuyProductIntroducePicBaseService ebuyProductIntroducePicBaseService;

	@Resource
	private EbuyDeliveryMethodBaseDao ebuyDeliveryMethodBaseDao;
	@Resource
	private EbuySupplyMerchantBaseDao ebuySupplyMerchantBaseDao;
	@Resource
	private EbuySupplyMerchantLicenceBaseDao ebuySupplyMerchantLicenceBaseDao;
	@Resource
	private EbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao;
	@Resource
	private EbuySupplyMerchantHasGroupBuildingBaseDao ebuySupplyMerchantHasGroupBuildingBaseDao;
	@Resource
	private EbuySupplyMerchantHasUserBaseDao ebuySupplyMerchantHasUserBaseDao;
	@Resource
	private IEbuySupplyMerchantBankAccountBaseService ebuySupplyMerchantBankAccountBaseService;
	@Resource
	private ISupplyMerchantDeliveryFeeSettlementBaseService supplyMerchantDeliveryFeeSettlementBaseService;

	@Resource
	private IOmsUserBaseService omsUserBaseService;

	@Resource
	private IOmsUserHasTEbuySupplyMerchantBaseService omsUserHasTEbuySupplyMerchantBaseService;

	@Resource
	private IOmsUserHasTOmsPermiRoleBaseService omsUserHasTOmsPermiRoleBaseService;

	@Resource
	private IOmsPermiRoleBaseService omsPermiRoleBaseService;

	@Resource
	private IUserBaseService userBaseService;

	@Resource
	private ICommMobileService commMobileService;

	@Resource
	private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

	@Resource
	private IRedpointContentBaseService redpointContentBaseService;



	public List<MerchantListDto> getEbuySupplyMerchantType1(Map<String, Object> param, PageModel pageModel) {
		if (pageModel != null) {
			param.put("begin", pageModel.getBegin());
			param.put("length", pageModel.getLength());
		}
		return ebuyMerchantDao.getEbuySupplyMerchantType1(param);
	}
	public Long getEbuySupplyMerchantType1Count(Map<String, Object> param) {
		return ebuyMerchantDao.getEbuySupplyMerchantType1Count(param);
	}
	public EbuyMerchantBean getEbuyMerchantByUserId(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return ebuyMerchantDao.getEbuyMerchant(paramMap);
	}

	public EbuyMerchantBean getEbuyMerchant(Map<String, Object> paramMap) {
		return ebuyMerchantDao.getEbuyMerchant(paramMap);
	}


	public List<MerchantOrder> getEbuyDeliveryOrderList(Map<String, Object> paramMap, PageModel pageModel) {
		return ebuyMerchantDao.getEbuyDeliveryOrderList(paramMap, pageModel);
	}

	public List<MerchantProdLst> getMerchantProductList(Map<String, Object> paramMap, PageModel pageModel) {
		return ebuyMerchantDao.getMerchantProductList(paramMap, pageModel);
	}

	public List<EbuyProdType> getProdTypes(Map<String, Object> paramMap) {
		return ebuyMerchantDao.getProdTypes(paramMap);
	}

	public List<EbuyProdType> getAllProdTypes() {
		return ebuyMerchantDao.getAllProdTypes();
	}

	public MerchantProdDetail getMerchantProdDetail(Map<String, Object> paramMap) {
		return ebuyMerchantDao.getMerchantProdDetail(paramMap);
	}

	public void insertMerchantProduct(MerchantEbuyProduct prod,List<EbuyProductIntroducePic> introduceList) {
		prod.setSelNum(BigInteger.valueOf(0));
		prod.setFilmTicketNum(0);
		prod.setPointType(1);
		prod.setWlappType(1L);
		prod.setSpecialProductType(1);
		prod.setPurchasePrice(prod.getPriceDiscount());

		String now = CnfantasiaCommbusiUtil.getCurrentTime();
		if (prod.getStatusAudit() != MerchantProdDetail.STATUS_AUDIT_REPERTORY) {
			//20170217临时需求，商户端直接上架
			prod.setStatus(0);
			prod.setStatusAudit(5);
			prod.setUpShelfTime(now);
		}


		prod.setIsPreSell(0);
		ebuyProductBaseService.insertEbuyProduct(prod);

		EbuyMerchantUtil.newStandard(prod.getProdShelf(), null);
		prod.getProdShelf().setApplyTime(now);
		EbuyProductShelf productShelf = prod.getProdShelf();
		if (prod.getStatusAudit() != MerchantProdDetail.STATUS_AUDIT_REPERTORY) {
			//20170217临时需求，商户端直接上架
			productShelf.setHandTime(now);
			productShelf.setUpShelfState(0);
		}

		ebuyProductShelfBaseService.insertEbuyProductShelf(productShelf);

		EbuyMerchantUtil.newStandardList(prod.getProdPic(), SEQConstants.t_ebuy_product_pic);
		ebuyProductPicBaseService.insertEbuyProductPicBatch(prod.getProdPic());

		if(introduceList!=null && introduceList.size()>0){
			List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_introduce_pic,introduceList.size());
			int index = 0;
			for(int i=0;i<introduceList.size();i++){
				introduceList.get(i).setId(idList.get(i));
			}
			ebuyProductIntroducePicBaseService.insertEbuyProductIntroducePicBatch(introduceList);
		}


		if(prod.getProdParams() != null && prod.getProdParams().size() > 0) {
			EbuyMerchantUtil.newStandardList(prod.getProdParams(), SEQConstants.t_ebuy_product_parameters);
			ebuyProductParametersBaseService.insertEbuyProductParametersBatch(prod.getProdParams());
		}
	}

	public void updateMerchantProduct(MerchantEbuyProduct prod, List<BigInteger> delPicIdList) {
		EbuyMerchantUtil.updateStandard(prod);
		ebuyProductBaseService.updateEbuyProduct(prod);

		EbuyMerchantUtil.updateStandard(prod.getProdShelf());
//		ebuyProductShelfBaseService.updateEbuyProductShelf(prod.getProdShelf());
		EbuyProductShelf productShelf = prod.getProdShelf();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productId", productShelf.gettEbuyProductId());
		param.put("productTypeId",  productShelf.gettEbuyProductTypeId());
		param.put("price", productShelf.getPrice());
		param.put("priceDiscount", productShelf.getPriceDiscount());
		param.put("upShelfState", productShelf.getUpShelfState());
		param.put("appType", 1);
		ebuyMerchantDao.updateShelfPriceByProductId(param);
		param.put("appType", 3);
		ebuyMerchantDao.updateShelfPriceByProductId(param);

		if(delPicIdList != null && delPicIdList.size() > 0) {
			ebuyProductPicBaseService.deleteEbuyProductPicLogicBatch(delPicIdList);
		}

		if(prod.getProdPic() != null && prod.getProdPic().size() > 0) {
			EbuyMerchantUtil.newStandardList(prod.getProdPic(), SEQConstants.t_ebuy_product_pic);
			ebuyProductPicBaseService.insertEbuyProductPicBatch(prod.getProdPic());
		}

		if(prod.getProdParams() != null && prod.getProdParams().size() > 0) {
			deletePodParams(prod.getId());
			EbuyMerchantUtil.newStandardList(prod.getProdParams(), SEQConstants.t_ebuy_product_parameters);
			ebuyProductParametersBaseService.insertEbuyProductParametersBatch(prod.getProdParams());
		}
	}


	/** 添加电商，并建立起user与Merchant之间的关系
	 * @param userId 
	 * @param selfGetAddress */
	@Transactional
	public void insertEbuySupplyMerchant(Long userId, ShopInfo shopInfo, double leastDeliveryAmt, double leastOrderAmt, double orderAmtPer,
			double orderAmtPerMust, String selfGetAddress) {
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		ebuySupplyMerchant.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_supply_merchant));
		ebuySupplyMerchant.setName(shopInfo.getShopName());
		ebuySupplyMerchant.setType(2);// 1:自营;2:附近商家;3:物业专供
		ebuySupplyMerchant.setAddress(shopInfo.getShopAddress());
		ebuySupplyMerchant.setTel(shopInfo.getLinkPhone());
		ebuySupplyMerchant.setStoreAuditStatus(0);
		ebuySupplyMerchant.setOwnerAuditStatus(0);
		ebuySupplyMerchant.setExpressType(1);
		ebuySupplyMerchant.setRevenueRate(0D);
		ebuySupplyMerchant.setRevenueType(EbuySupplyMerchantConstant.RevenueType.AGENT);
		ebuySupplyMerchant.setLinkName(shopInfo.getLinkName());
		ebuySupplyMerchant.settAddressBlockFId(shopInfo.getBlockId());

		String storePic = "";
		for (String img : shopInfo.getShopPicList()) {
			storePic += img + ";";
		}
		ebuySupplyMerchant.setShopPhotoes(storePic);

		ebuySupplyMerchant.setIsfetch(0);
		ebuySupplyMerchant.setAutoConfirmReceivePeriod(10);
		ebuySupplyMerchant.setIntroduce(shopInfo.getIntroduce());
		ebuySupplyMerchant.setLeastDeliveryAmt(leastDeliveryAmt > 0 ? PriceUtil.multiply100(leastDeliveryAmt) : 0);
		ebuySupplyMerchantBaseDao.insertEbuySupplyMerchant(ebuySupplyMerchant);

		if (shopInfo.getShopLicenseList().size() > 0) {
			List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList = new ArrayList<EbuySupplyMerchantLicence>(shopInfo.getShopLicenseList()
					.size());
			List<BigInteger> idList = CnfantasiaCommbusiUtil.getUuidList(SEQConstants.t_ebuy_supply_merchant_licence, shopInfo.getShopLicenseList()
					.size());

			for (int i = 0; i < shopInfo.getShopLicenseList().size(); i++) {
				String img = shopInfo.getShopLicenseList().get(i);
				EbuySupplyMerchantLicence ebuySupplyMerchantLicence = new EbuySupplyMerchantLicence();
				ebuySupplyMerchantLicence.setId(idList.get(i));
				ebuySupplyMerchantLicence.settSupplyMcId(ebuySupplyMerchant.getId());
				ebuySupplyMerchantLicence.setUrl(img);
				ebuySupplyMerchantLicenceList.add(ebuySupplyMerchantLicence);
			}
			ebuySupplyMerchantLicenceBaseDao.insertEbuySupplyMerchantLicenceBatch(ebuySupplyMerchantLicenceList);
		}

		EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser = new EbuySupplyMerchantHasUser();
		ebuySupplyMerchantHasUser.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_supply_merchant_has_user));
		ebuySupplyMerchantHasUser.setUpdateTm(DateUtil.formatSecond.get().format(new Date()));
		ebuySupplyMerchantHasUser.settMerchantId(ebuySupplyMerchant.getId().toString());
		ebuySupplyMerchantHasUser.settUserId(userId.toString());
		ebuySupplyMerchantHasUser.setIsFirstLogin(0);
		ebuySupplyMerchantHasUserBaseDao.insertEbuySupplyMerchantHasUser(ebuySupplyMerchantHasUser);

		// 服务小区保存
		if (shopInfo.getServiceGbIds() != null && shopInfo.getServiceGbIds().length > 0) {
			List<BigInteger> idList = CnfantasiaCommbusiUtil.getUuidList(SEQConstants.t_ebuy_supply_merchant_has_group_building,
					shopInfo.getServiceGbIds().length);
			List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList = new ArrayList<EbuySupplyMerchantHasGroupBuilding>();
			for (int i = 0; i < shopInfo.getServiceGbIds().length; i++) {
				EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
				ebuySupplyMerchantHasGroupBuilding.setId(idList.get(i));
				ebuySupplyMerchantHasGroupBuilding.settEbuySupplyMerchantId(ebuySupplyMerchant.getId());
				ebuySupplyMerchantHasGroupBuilding.settGroupBuildingId(new BigInteger(shopInfo.getServiceGbIds()[i]));
				ebuySupplyMerchantHasGroupBuildingList.add(ebuySupplyMerchantHasGroupBuilding);
			}
			ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuildingBatch(ebuySupplyMerchantHasGroupBuildingList);
		}

		insertEbuyDeliveryMethod(false, leastDeliveryAmt, leastOrderAmt, orderAmtPer, orderAmtPerMust,selfGetAddress, ebuySupplyMerchant);
	}

	/** 更新配送方式，并建立与ebuySupplyMerchant的关系
	 * @param leastDeliveryAmt
	 * @param leastOrderAmt
	 * @param orderAmtPer
	 * @param orderAmtPerMust
	 * @param selfGetAddress 自提点地址
	 * @param ebuySupplyMerchant */
	@Transactional
	public void updateEbuyDeliveryMethod(double leastDeliveryAmt, double leastOrderAmt, double orderAmtPer, double orderAmtPerMust,
			String selfGetAddress, BigInteger esmId) {

		ebuyMerchantDao.deleteDeliveryMethodByMerchantId(esmId);
		
		if(StringUtils.isNotBlank(selfGetAddress)){
			updateESMLeastDeliveryAmount(0, esmId);
			
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(0L);
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName(selfGetAddress);
			ebuyDeliveryMethod.setDesc("到店自提");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setType(EbuyDict.EbuyDeliveryMethodType.SelfGet);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(esmId);
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
			return;
		}

		if (leastDeliveryAmt >= 0) { // 设置了起送金额的，不需要快递费
			updateESMLeastDeliveryAmount(leastDeliveryAmt, esmId);

			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(0L);
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName("满"+ leastDeliveryAmt + "元免运费");
			ebuyDeliveryMethod.setDesc("满"+ leastDeliveryAmt + "元免运费");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(esmId);
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		} else if (leastOrderAmt > 0 && orderAmtPer >= 0) {//订单少于 leastOrderAmt,收费orderAmtPer
			updateESMLeastDeliveryAmount(0, esmId);

			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(PriceUtil.multiply100(orderAmtPer));
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName(leastOrderAmt + "以内需收费");
			ebuyDeliveryMethod.setDesc(leastOrderAmt + "以内需收费");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setPriceAmountEnd(PriceUtil.multiply100(leastOrderAmt));
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(esmId);
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);

			ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(0L);
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName("满" + leastOrderAmt + "免运费");
			ebuyDeliveryMethod.setDesc("满" + leastOrderAmt + "免运费");
			ebuyDeliveryMethod.setPriceAmountStart(PriceUtil.multiply100(leastOrderAmt));
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		} else if (orderAmtPerMust >= 0) {
			updateESMLeastDeliveryAmount(0, esmId);
			
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(PriceUtil.multiply100(orderAmtPerMust));
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName((orderAmtPerMust) + "元/单，不含港澳台及海外地区");
			ebuyDeliveryMethod.setDesc((orderAmtPerMust) + "元/单，不含港澳台及海外地区");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(esmId);
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		}
	}
	
	private void updateESMLeastDeliveryAmount(double leastDeliveryAmt, BigInteger esmId) {
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		ebuySupplyMerchant.setId(esmId);
		ebuySupplyMerchant.setLeastDeliveryAmt(PriceUtil.multiply100(leastDeliveryAmt));
		ebuySupplyMerchantBaseDao.updateEbuySupplyMerchant(ebuySupplyMerchant);

		EbuySupplyMerchantEntity merchantSupply = (EbuySupplyMerchantEntity) SessionManager.getSession().getAttribute("merchantSupply");
		if (merchantSupply != null) {
			merchantSupply.setLeastDeliveryAmt(PriceUtil.multiply100(leastDeliveryAmt));
		}
	}

	/** 插入配送方式，并建立与ebuySupplyMerchant的关系
	 * @param isFreeDelivery 是否包邮
	 * @param leastDeliveryAmt 起邮价
	 * @param leastOrderAmt 多少以内收邮费
	 * @param orderAmtPer 不满指定金额收多少邮费
	 * @param orderAmtPerMust 定邮价，不论多少都收一个价
	 * @param selfGetAddress 自提点地址
	 * @param ebuySupplyMerchant 供应商
	 */
	private void insertEbuyDeliveryMethod(boolean isFreeDelivery, double leastDeliveryAmt, double leastOrderAmt,
										  double orderAmtPer, double orderAmtPerMust, String selfGetAddress, EbuySupplyMerchant ebuySupplyMerchant) {
		if (isFreeDelivery) { //包邮
			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(new BigInteger("-1"));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(ebuySupplyMerchant.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
			return;
		}
		
		if(StringUtils.isNotBlank(selfGetAddress)){//顾客自提
			updateESMLeastDeliveryAmount(0, ebuySupplyMerchant.getId());
			
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(0L);
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName(selfGetAddress);
			ebuyDeliveryMethod.setDesc("到店自提");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setType(EbuyDict.EbuyDeliveryMethodType.SelfGet);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(ebuySupplyMerchant.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
			return;
		}

		if (leastDeliveryAmt >= 0) { // 设置了起送金额的，不需要快递费
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(0L);
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName("满"+PriceUtil.removeTailZero(new BigDecimal(leastDeliveryAmt).setScale(2, RoundingMode.HALF_UP)) + "元免运费");
			ebuyDeliveryMethod.setDesc("满"+PriceUtil.removeTailZero(new BigDecimal(leastDeliveryAmt).setScale(2, RoundingMode.HALF_UP)) + "元免运费");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(ebuySupplyMerchant.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		} else if (leastOrderAmt > 0 && orderAmtPer >= 0) { //不足leastOrderAmt收邮费orderAmtPer
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(PriceUtil.multiply100(orderAmtPer));
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName(PriceUtil.removeTailZero(new BigDecimal(leastOrderAmt).setScale(2, RoundingMode.HALF_UP)) + "以内需收费");
			ebuyDeliveryMethod.setDesc(PriceUtil.removeTailZero(new BigDecimal(leastOrderAmt).setScale(2, RoundingMode.HALF_UP)) + "以内需收费");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setPriceAmountEnd(PriceUtil.multiply100(leastOrderAmt));
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(ebuySupplyMerchant.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);

			ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(0L);
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName("满" + PriceUtil.removeTailZero(new BigDecimal(leastOrderAmt).setScale(2, RoundingMode.HALF_UP)) + "免运费");
			ebuyDeliveryMethod.setDesc("满" + PriceUtil.removeTailZero(new BigDecimal(leastOrderAmt).setScale(2, RoundingMode.HALF_UP)) + "免运费");
			ebuyDeliveryMethod.setPriceAmountStart(PriceUtil.multiply100(leastOrderAmt));
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		} else if (orderAmtPerMust >= 0) {
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_ebuy_delivery_method));
			ebuyDeliveryMethod.setFee(PriceUtil.multiply100(orderAmtPerMust));
			ebuyDeliveryMethod.setFastTime(0L);
			ebuyDeliveryMethod.setName(PriceUtil.removeTailZero(new BigDecimal(orderAmtPerMust).setScale(2, RoundingMode.HALF_UP)) + "元/单，不含港澳台及海外地区");
			ebuyDeliveryMethod.setDesc(PriceUtil.removeTailZero(new BigDecimal(orderAmtPerMust).setScale(2, RoundingMode.HALF_UP)) + "元/单，不含港澳台及海外地区");
			ebuyDeliveryMethod.setPriceAmountStart(0L);
			ebuyDeliveryMethod.setType(1);
			ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);

			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(CnfantasiaCommbusiUtil
					.getUuid(SEQConstants.t_ebuy_supply_merchant_has_t_ebuy_delivery_method));
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuyDeliveryMethodFId(ebuyDeliveryMethod.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethod.settEbuySupplyMerchantFId(ebuySupplyMerchant.getId());
			ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		}
	}

	/**
	 * 新增自营供应商
	 * @param dto 参数封装类
     */
	@Transactional
	public void addEbuySupplyMerchantType1(EbuySupplyMerchantDto dto) {
		//添加供应商
		EbuySupplyMerchant ebuySupplyMerchant = dto.getEbuySupplyMerchant();
		ebuySupplyMerchant.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant));
		if (ebuySupplyMerchant.getRevenueType() != null && ebuySupplyMerchant.getRevenueType().compareTo(2) == 0) {
			ebuySupplyMerchant.setRevenueRate(ebuySupplyMerchant.getRevenueRate()/100);
		}
		ebuySupplyMerchant.setStoreAuditStatus(1);
		ebuySupplyMerchant.setOwnerAuditStatus(1);
		ebuySupplyMerchant.setAutoConfirmReceivePeriod(10);
		ebuySupplyMerchantBaseDao.insertEbuySupplyMerchant(ebuySupplyMerchant);

		//处理供应商银行信息
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		EbuySupplyMerchantBankAccount bankAccount = dto.getEbuySupplyMerchantBankAccount();
		if (!StringUtils.isEmpty(bankAccount.getAccountName())&& !StringUtils.isEmpty(bankAccount.getAccountBank())) {
			bankAccount.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_bank_account));
			bankAccount.settSupplyMerchantId(ebuySupplyMerchant.getId());
			bankAccount.setSys0UpdTime(now);
			ebuySupplyMerchantBankAccountBaseService.insertEbuySupplyMerchantBankAccount(bankAccount);
		}

		//处理自营是全国还是城市,城市做处理
		if (ebuySupplyMerchant.getType().compareTo(4) == 0 && !DataUtil.isEmpty(dto.getServeCityIds())) {
			List<BigInteger> cities = new ArrayList<BigInteger>(dto.getServeCityIds());
			dealSupplyMerchantCity(cities, ebuySupplyMerchant.getId());
		}
		//处理运费
		insertEbuyDeliveryMethod(dto.getDeliveryType().compareTo(1) == 0, -1, dto.getDeliveryFeeFreeStart() == null ? 0L : dto.getDeliveryFeeFreeStart(),
				dto.getDeliveryFee() == null ? 0L : dto.getDeliveryFee(), 0,"", ebuySupplyMerchant);
		//处理结算费购销模式
		if (ebuySupplyMerchant.getRevenueType() != null && ebuySupplyMerchant.getRevenueType().equals(1)) {
			Double settleFreeStart = dto.getSettleDeliveryFeeFreeStart();
			Double settltFee = dto.getSettleDeliveryFee();
			dealMerchantDevlivFeeSettlement(settleFreeStart, settltFee, ebuySupplyMerchant.getId());
		}

		//添加运营平台账号
		addMerchantOmsUser(ebuySupplyMerchant.getId(), dto.getUserAccount(), Md5Util.MD5Twice(dto.getPassword()));
	}

	/**
	 * 修改自营供应商
	 * @param dto 参数封装类
	 */
	@Transactional
	public void updEbuySupplyMerchantType1(EbuySupplyMerchantDto dto) {
		//供应商处理
		EbuySupplyMerchant ebuySupplyMerchant = dto.getEbuySupplyMerchant();
		if (ebuySupplyMerchant.getRevenueType() != null) {
			ebuySupplyMerchant.setRevenueRate(ebuySupplyMerchant.getRevenueRate() / 100);
		}
		ebuySupplyMerchantBaseDao.updateEbuySupplyMerchant(ebuySupplyMerchant);
		//供应商银行账户处理, 有可能之前没录入
		EbuySupplyMerchantBankAccount bankAccount = dto.getEbuySupplyMerchantBankAccount();
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		bankAccount.setSys0UpdTime(now);
		if (bankAccount.getId() == null) {
			if (!StringUtils.isEmpty(bankAccount.getAccountName())&& !StringUtils.isEmpty(bankAccount.getAccountBank())) {
				bankAccount.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_bank_account));
				bankAccount.settSupplyMerchantId(ebuySupplyMerchant.getId());
				ebuySupplyMerchantBankAccountBaseService.insertEbuySupplyMerchantBankAccount(bankAccount);
			}
		} else {
			ebuySupplyMerchantBankAccountBaseService.updateEbuySupplyMerchantBankAccount(bankAccount);
		}
		//处理关联城市
		ebuyMerchantDao.deleteGroupBuildingByMerchantId(ebuySupplyMerchant.getId());
		//处理自营是全国还是城市,城市做处理
		if (ebuySupplyMerchant.getType().compareTo(4) == 0 && !DataUtil.isEmpty(dto.getServeCityIds())) {
			List<BigInteger> cities = new ArrayList<BigInteger>(dto.getServeCityIds());
			dealSupplyMerchantCity(cities, ebuySupplyMerchant.getId());
		}
		//处理运费
		ebuyMerchantDao.deleteDeliveryMethodByMerchantId(ebuySupplyMerchant.getId());
		insertEbuyDeliveryMethod(dto.getDeliveryType().compareTo(1) == 0, -1, dto.getDeliveryFeeFreeStart() == null ? 0L : dto.getDeliveryFeeFreeStart(),
				dto.getDeliveryFee() == null ? 0L : dto.getDeliveryFee(), 0, "", ebuySupplyMerchant);
		//处理结算费购销模式
		ebuyMerchantDao.deleteMerchantDelivFeeSettlementByMerchantId(ebuySupplyMerchant.getId());
		if (ebuySupplyMerchant.getRevenueType() != null && ebuySupplyMerchant.getRevenueType().equals(1)) {
			Double settleFreeStart = dto.getSettleDeliveryFeeFreeStart();
			Double settltFee = dto.getSettleDeliveryFee();
			dealMerchantDevlivFeeSettlement(settleFreeStart, settltFee, ebuySupplyMerchant.getId());
		}

	}
	private void dealSupplyMerchantCity(List<BigInteger> cities, BigInteger supplyMerchantId) {
		if (!DataUtil.isEmpty(cities)) {
			EbuySupplyMerchantHasGroupBuilding hasGroupBuilding = null;
			List<EbuySupplyMerchantHasGroupBuilding> hasGroupBuildingList = new ArrayList<EbuySupplyMerchantHasGroupBuilding>();
			List<BigInteger> hasGbIds = uuidManager.
					getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_has_group_building, cities.size());
			for (int i = 0; i < cities.size(); i++) {
				hasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
				hasGroupBuilding.setId(hasGbIds.get(i));
				hasGroupBuilding.settEbuySupplyMerchantId(supplyMerchantId);
				hasGroupBuilding.settAddressCityId(cities.get(i));
				hasGroupBuildingList.add(hasGroupBuilding);
			}
			ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuildingBatch(hasGroupBuildingList);
		}
	}
	private void dealMerchantDevlivFeeSettlement(Double settleFreeStart, Double settltFee, BigInteger merchantId) {
		SupplyMerchantDeliveryFeeSettlement settleFee = null;
		if (settleFreeStart > 0) {
			List<BigInteger> feeIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_supply_merchant_delivery_fee_settlement, 2);
			List<SupplyMerchantDeliveryFeeSettlement> settleFees = new ArrayList<SupplyMerchantDeliveryFeeSettlement>();

			settleFee = new SupplyMerchantDeliveryFeeSettlement();
			settleFee.setId(feeIds.get(0));
			settleFee.settEbuySupplyMerchantFId(merchantId);
			settleFee.setSettlementFee(PriceUtil.multiply100(settltFee));
			settleFee.setAmountStart(0L);
			settleFee.setAmountEnd(PriceUtil.multiply100(settleFreeStart));
			settleFees.add(settleFee);
			settleFee = new SupplyMerchantDeliveryFeeSettlement();
			settleFee.setId(feeIds.get(1));
			settleFee.settEbuySupplyMerchantFId(merchantId);
			settleFee.setSettlementFee(0L);
			settleFee.setAmountStart(PriceUtil.multiply100(settleFreeStart));
			settleFees.add(settleFee);
			supplyMerchantDeliveryFeeSettlementBaseService.insertSupplyMerchantDeliveryFeeSettlementBatch(settleFees);
		} else {
			settleFee = new SupplyMerchantDeliveryFeeSettlement();
			settleFee.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_supply_merchant_delivery_fee_settlement));
			settleFee.settEbuySupplyMerchantFId(merchantId);
			settleFee.setSettlementFee(0L);
			settleFee.setAmountStart(0L);
			supplyMerchantDeliveryFeeSettlementBaseService.insertSupplyMerchantDeliveryFeeSettlement(settleFee);
		}
	}
	
	public MerchantEbuyProduct getMerchantProduct(Map<String, Object> paramMap) {
		return ebuyMerchantDao.getMerchantProduct(paramMap);
	}
	
	public EbuySupplyMerchantEntity getMerchantSupply(Map<String, Object> paramMap) {
		return ebuyMerchantDao.getMerchantSupply(paramMap);
	}
	
	
	public void setEbuyMerchantDao(EbuyMerchantDao ebuyMerchantDao) {
		this.ebuyMerchantDao = ebuyMerchantDao;
	}
	
	private int deletePodParams(BigInteger prodId) {
		return this.ebuyMerchantDao.deletePodParams(prodId);
	}
	
	public int updateEbuyMerchant(Map<String, Object> paramMap) {
		return this.ebuyMerchantDao.updateEbuyMerchant(paramMap);
	}

	public void setEbuyProductBaseService(
			IEbuyProductBaseService ebuyProductBaseService) {
		this.ebuyProductBaseService = ebuyProductBaseService;
	}

	public void setEbuyProductPicBaseService(
			IEbuyProductPicBaseService ebuyProductPicBaseService) {
		this.ebuyProductPicBaseService = ebuyProductPicBaseService;
	}

	public void setEbuyProductParametersBaseService(
			IEbuyProductParametersBaseService ebuyProductParametersBaseService) {
		this.ebuyProductParametersBaseService = ebuyProductParametersBaseService;
	}

	public void setEbuyProductShelfBaseService(
			IEbuyProductShelfBaseService ebuyProductShelfBaseService) {
		this.ebuyProductShelfBaseService = ebuyProductShelfBaseService;
	}

	public int qrySupplyMerchantList_forCount(Map<String, Object> paramMap) {
		return ebuyMerchantDao.qrySupplyMerchantList_forCount(paramMap);
	}

	public List<EbuySupplyMerchant4List> qrySupplyMerchantList(Map<String, Object> paramMap) {
		return ebuyMerchantDao.qrySupplyMerchantList(paramMap);
	}

	public Merchant4AuditBean getMerchat4AuditOrView(String id) {
		return ebuyMerchantDao.getMerchat4AuditOrView(id);
	}

	@Transactional
	public void updateEbuySupplyMerchant(Long userId, BigInteger id, ShopInfo shopInfo) {
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		{
			ebuySupplyMerchant.setId(id);
			ebuySupplyMerchant.setSys0UpdUser(new BigInteger(userId+""));
			ebuySupplyMerchant.setName(shopInfo.getShopName());
			ebuySupplyMerchant.setAddress(shopInfo.getShopAddress());
			ebuySupplyMerchant.setTel(shopInfo.getLinkPhone());
			ebuySupplyMerchant.setLinkName(shopInfo.getLinkName());
			ebuySupplyMerchant.settAddressBlockFId(shopInfo.getBlockId());
			ebuySupplyMerchant.setStoreAuditStatus(0);
			ebuySupplyMerchant.setOwnerAuditStatus(0);
	
			String storePic = "";
			for (String img : shopInfo.getShopPicList()) {
				storePic += img + ";";
			}
			if(StringUtils.isNotEmpty(storePic)){
				ebuySupplyMerchant.setShopPhotoes(storePic);
			}
	
			ebuySupplyMerchant.setIntroduce(shopInfo.getIntroduce());		
			ebuySupplyMerchantBaseDao.updateEbuySupplyMerchant(ebuySupplyMerchant);
		}
		

		if (shopInfo.getShopLicenseList().size() > 0) {
			//删除之前的证书 
			ebuyMerchantDao.deleteLicenseByMerchantId(id);
			
			List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList = new ArrayList<EbuySupplyMerchantLicence>(shopInfo.getShopLicenseList()
					.size());
			List<BigInteger> idList = CnfantasiaCommbusiUtil.getUuidList(SEQConstants.t_ebuy_supply_merchant_licence, shopInfo.getShopLicenseList()
					.size());

			for (int i = 0; i < shopInfo.getShopLicenseList().size(); i++) {
				String img = shopInfo.getShopLicenseList().get(i);
				EbuySupplyMerchantLicence ebuySupplyMerchantLicence = new EbuySupplyMerchantLicence();
				ebuySupplyMerchantLicence.setId(idList.get(i));
				ebuySupplyMerchantLicence.settSupplyMcId(ebuySupplyMerchant.getId());
				ebuySupplyMerchantLicence.setUrl(img);
				ebuySupplyMerchantLicenceList.add(ebuySupplyMerchantLicence);
			}
			ebuySupplyMerchantLicenceBaseDao.insertEbuySupplyMerchantLicenceBatch(ebuySupplyMerchantLicenceList);
		}

		// 服务小区保存
		if (shopInfo.getServiceGbIds() != null && shopInfo.getServiceGbIds().length > 0) {
			//删除之前的服务小区
			ebuyMerchantDao.deleteGroupBuildingByMerchantId(id);
			
			List<BigInteger> idList = CnfantasiaCommbusiUtil.getUuidList(SEQConstants.t_ebuy_supply_merchant_has_group_building,
					shopInfo.getServiceGbIds().length);
			List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList = new ArrayList<EbuySupplyMerchantHasGroupBuilding>();
			for (int i = 0; i < shopInfo.getServiceGbIds().length; i++) {
				EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
				ebuySupplyMerchantHasGroupBuilding.setId(idList.get(i));
				ebuySupplyMerchantHasGroupBuilding.settEbuySupplyMerchantId(ebuySupplyMerchant.getId());
				ebuySupplyMerchantHasGroupBuilding.settGroupBuildingId(new BigInteger(shopInfo.getServiceGbIds()[i]));
				ebuySupplyMerchantHasGroupBuildingList.add(ebuySupplyMerchantHasGroupBuilding);
			}
			ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuildingBatch(ebuySupplyMerchantHasGroupBuildingList);
		}
	}

	public List<DeliveryMethod> selectDeliveryMethodByMerchantId(BigInteger id) {
		return ebuyMerchantDao.selectDeliveryMethodByMerchantId(id);
	}

	public List<DeliveryMethod> selectLastestDeliveryMethodCondition(Map<String, Object> param) {
		return ebuyMerchantDao.selectDeliveryMethodCondition(param);
	}
	
	public List<EbuyProductIntroducePic> selectIntroducePics(Map<String,Object> paramMap){
		return ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(paramMap);
	}
	
	public void insertOrUpdateOrDeletePics(List<EbuyProductIntroducePic> list,List<EbuyProductIntroducePic> list1,List<Integer> indexList
			,List<EbuyProductIntroducePic> oldlist,List<Integer> noDelindexList){
		String filePath= OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir ;
//		String filePath = "D:/usr/uploadImages";//本地测试路径
		//删除图片
		if(oldlist !=null && oldlist.size()>0){
			for(int a=0;a<oldlist.size();a++){
				if(noDelindexList.contains(a)){
					ebuyProductIntroducePicBaseService.deleteEbuyProductIntroducePicLogic(oldlist.get(a).getId());
				}else{
					//删除图片
					File fileC = new File(filePath,oldlist.get(a).getUrlBig());
					fileC.delete();
					ebuyProductIntroducePicBaseService.deleteEbuyProductIntroducePicLogic(oldlist.get(a).getId());
				}
			}
		}
		if(list1!=null && list1.size()>0){
			for(int i=0;i<list1.size();i++){
				//删除修改图片
				File fileC = new File(filePath,list1.get(i).getUrlBig());
				fileC.delete();
//				ebuyProductIntroducePicBaseService.deleteEbuyProductIntroducePicLogic(list1.get(i).getId());
			}
		}
		for(int a=0;a<list.size();a++){
			if(indexList.contains(a)){
				//新增insert
				list.get(a).setId( uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_introduce_pic));
				ebuyProductIntroducePicBaseService.insertEbuyProductIntroducePic(list.get(a));
			}else{
				//更新原先图片
				list.get(a).setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_introduce_pic));
				ebuyProductIntroducePicBaseService.insertEbuyProductIntroducePic(list.get(a));
			}
		}
		
	}

	public List<Map<String, Object>> getSupplyMerchantType1CityList(BigInteger supplyMerchantId) {
		return ebuyMerchantDao.getSupplyMerchantType1CityList(supplyMerchantId);
	}

	public SupplyMerchantDeliveryFeeSettlement getMerchantSettleFeeByAmountAndMerchantId(BigInteger merchantId, Long totalFee) {
		return ebuyMerchantDao.getMerchantSettleFeeByAmountAndMerchantId(merchantId, totalFee);
	}
	
	/**
	 * 查询供应商运费信息
	 * 
	 * @param merchantId
	 * @return
	 */
	public List<MerchantFeeDto> getMerchantFeeList(BigInteger merchantId){
		return ebuyMerchantDao.getMerchantFeeList(merchantId);
	}

	@Transactional
	public void regOmsUserAfterPass(BigInteger supplyMerchantId) {
		EbuySupplyMerchantHasUser merchantHasUser = new EbuySupplyMerchantHasUser();
		merchantHasUser.settMerchantId(supplyMerchantId.toString());
		List<EbuySupplyMerchantHasUser> users = ebuySupplyMerchantHasUserBaseDao.
				selectEbuySupplyMerchantHasUserByCondition(MapConverter.toMap(merchantHasUser), false);
		if (!DataUtil.isEmpty(users)) {
			User user = userBaseService.getUserBySeqId(new BigInteger(users.get(0).gettUserId()));
			addMerchantOmsUser(supplyMerchantId, user.getMobile(), Md5Util.MD5Twice(user.getMobile()));
//			String msg = SmsPropertyUtil.getProperty("manual_add_supply_merchant_oms_user", user.getMobile(), user.getMobile());
//			commMobileService.sendMsg(user.getMobile(), msg);
			ShortMsgUtil.sendMessage(user.getMobile(), "manual_add_supply", user.getMobile(), user.getMobile());
		}
	}

	public boolean isOmsUserExists(String userAccount) {
		OmsUser user = new OmsUser();
		user.setUserAccount(userAccount);
		return omsUserBaseService.getOmsUserCount(MapConverter.toMap(user)) > 0;
	}

	private void addMerchantOmsUser(BigInteger supplyMerchantId, String account, String passwordAfterEncode) {
		//添加后台账号
		OmsUser user = new OmsUser();
		user.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user));
		user.setUserAccount(account);
		user.setPassword(passwordAfterEncode);
		user.setIsadmin(0);
		user.setUserState(UserDict.UserState.IN_USE);
		user.setIsSubUser(0);
		user.setIsPmUser(0);
		user.setSys0DelState(0);
		omsUserBaseService.insertOmsUser(user);
		//关联与供应商的关系
		OmsUserHasTEbuySupplyMerchant userHasMerchant = new OmsUserHasTEbuySupplyMerchant();
		userHasMerchant.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_ebuy_supply_merchant));
		userHasMerchant.settOmsUserId(user.getId());
		userHasMerchant.settEbuySupplyMerchantId(supplyMerchantId);
		omsUserHasTEbuySupplyMerchantBaseService.insertOmsUserHasTEbuySupplyMerchant(userHasMerchant);
		//分配权限
		OmsPermiRole permiRole = new OmsPermiRole();
		permiRole.setCode("Ebuy_Supply");
		List<OmsPermiRole> roles = omsPermiRoleBaseService.getOmsPermiRoleByCondition(MapConverter.toMap(permiRole));
		if (!DataUtil.isEmpty(roles)) {
			OmsUserHasTOmsPermiRole userHasTOmsPermiRole = new OmsUserHasTOmsPermiRole();
			userHasTOmsPermiRole.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_oms_permi_role));
			userHasTOmsPermiRole.settOmsUserFId(user.getId());
			userHasTOmsPermiRole.settOmsPermiRoleFId(roles.get(0).getId());
			omsUserHasTOmsPermiRoleBaseService.insertOmsUserHasTOmsPermiRole(userHasTOmsPermiRole);
		}
	}

	public List<SettleDelivOrder> getOrderNotApplySettle(BigInteger supplyMerchantId, PageModel pageModel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplyMerchantId", supplyMerchantId);
		param.put("_begin", pageModel.getBegin());
		param.put("_length", pageModel.getLength());
		return ebuyMerchantDao.getOrderNotApplySettle(param);
	}

	/**
	 * 获取运单详情
	 * @param deliveryOrderId 运单ID
	 * @param supplyMerchantId 供应商ID，不传也可以，传了防止用户乱拿信息
     * @return 运单详情
     */
	public DeliveryOrderDetailEntity getDeliveryOrderDetail(BigInteger deliveryOrderId, BigInteger supplyMerchantId) {
		return ebuyMerchantDao.getDeliveryOrderDetail(deliveryOrderId, supplyMerchantId);
	}

	@Transactional
	public void updateOrInsertBankAccount(BigInteger supplyMerchantId, String withdrawPassword, EbuySupplyMerchantBankAccount bankAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tSupplyMerchantId", supplyMerchantId);
		List<EbuySupplyMerchantBankAccount> bankAccounts = ebuySupplyMerchantBankAccountBaseService.getEbuySupplyMerchantBankAccountByCondition(paramMap);
		//不存在则添加
		if (DataUtil.isEmpty(bankAccounts)) {
			bankAccount.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_bank_account));
			bankAccount.settSupplyMerchantId(supplyMerchantId);
			ebuySupplyMerchantBankAccountBaseService.insertEbuySupplyMerchantBankAccount(bankAccount);

			EbuySupplyMerchant supplyMerchant = new EbuySupplyMerchant();
			supplyMerchant.setId(supplyMerchantId);
			supplyMerchant.setWithdrawPassword(EncodeImpl.doEncodePassword(withdrawPassword));
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(supplyMerchant);
		} else { //存在则更新
			bankAccount.setId(bankAccounts.get(0).getId());
			ebuySupplyMerchantBankAccountBaseService.updateEbuySupplyMerchantBankAccount(bankAccount);

			//删除多余的条数
			if (bankAccounts.size() > 1) {
				List<BigInteger> delIds = new ArrayList<BigInteger>();
				for (int i = 0; i < bankAccounts.size(); i++) {
					if (i > 1) {
						delIds.add(bankAccounts.get(i).getId());
					}
				}
				if (delIds.size() > 0) {
					ebuySupplyMerchantBankAccountBaseService.deleteEbuySupplyMerchantBankAccountLogicBatch(delIds);
				}
			}
		}
	}

	/**
	 * 查询结算列表
	 * @param supplyMerchantId 供应商ID
	 * @param settleStatusType 结算状态：1 结算中（包括不通过） 2 已结算（即审核通过）
	 * @param pageModel 分页信息
     * @return 结算列表
     */
	public List<RevenueApplyDto> getRevenueApplyList(BigInteger supplyMerchantId, Integer settleStatusType, PageModel pageModel) {
		return ebuyMerchantDao.getRevenueApplyList(supplyMerchantId, settleStatusType, pageModel);
	}

	public Integer getRevenueApplyListCount(BigInteger supplyMerchantId, Integer settleStatusType) {
		return ebuyMerchantDao.getRevenueApplyListCount(supplyMerchantId, settleStatusType);
	}

	/**
	 * 根据结算申请ID查找结算订单列表
	 * @param supplyMerchantId 供应商ID，可以不传，传了防止其它供应商乱查
	 * @param applyId 结算申请ID
	 * @param pageModel 分页
     * @return 结算订单列表
     */
	public List<DeliveryOrderDetailEntity> getSettleDeliveryOrderList(BigInteger supplyMerchantId, BigInteger applyId, PageModel pageModel) {
		return ebuyMerchantDao.getSettleDeliveryOrderList(supplyMerchantId, applyId, pageModel);
	}

	public boolean hasNotClickRedPoint(String modelCode, BigInteger supplyMerchantId) {
		RedpointContent redpointContent = new RedpointContent();
		redpointContent.setModelCode(modelCode);
		redpointContent.setUserType(1);
		redpointContent.setUserId(supplyMerchantId);
		redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);
		return redpointContentBaseService.getRedpointContentCount(MapConverter.toMap(redpointContent)) > 0;
	}

	public List<CommUserDataEntity> getUserDataInMerchantService(BigInteger merchantId) {
		return ebuyMerchantDao.getUserDataInMerchantService(merchantId);
	}

    public void synSendPushAfterAddProduct(BigInteger limitBuyId, BigInteger merchantId) {
        //redis判断是否2小时内有推送
        String key = RedisCachePrefix.Merchant_Add_Limit_Buy_Push + merchantId;
        String value = RedisCacheHandler.get(key);
        if (value != null) {
            return;
        }
        RedisCacheHandler.set(key, "1", 60 * 60 * 2);

        List<CommUserDataEntity> userList = getUserDataInMerchantService(merchantId);
        EbuySupplyMerchant merchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
        MsgToAddBasic basic = new MsgToAddBasic();
        basic.setTitle("限时促销");
        basic.setContent(merchant.getName() + "正在搞促销啦，快来瞅一瞅吧");
        basic.setIsReleatRoom(MsgpushDict.PushReleatRoom.TRUE);
        basic.setiOSMsgType(NoticeDict.Message_Type.MerchantAddLimitBuy);
        basic.setAndroidPushId(MsgpushDict.PushId.MerchantAddLimitBuy);

		//参数信息
		Map<String, Object> msgParams = new HashMap<String, Object>();
		msgParams.put("storeId", merchantId);
		basic.setMsgParameters(msgParams);

		pushAddService.addMessage2Pool(limitBuyId, userList, basic);
    }

	/**
	 * 配置楼下店 小区范围
	 * @param gbIds
	 * @param shopId
     * @return
     */
	public String addStoreRange(String[] gbIds, BigInteger shopId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tEbuySupplyMerchantId", shopId);
        List<EbuySupplyMerchantHasGroupBuilding> addList = new ArrayList<EbuySupplyMerchantHasGroupBuilding>();
        List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildings = ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paraMap, false);
        if(!DataUtil.isEmpty(ebuySupplyMerchantHasGroupBuildings)) {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_has_group_building, gbIds.length);
            for (int i = 0; i < gbIds.length; i++) {
                boolean hasGb = true;
                BigInteger gbId = BigInteger.valueOf(Long.valueOf(gbIds[i]));
                for (EbuySupplyMerchantHasGroupBuilding supplyMerchantHasGroupBuilding : ebuySupplyMerchantHasGroupBuildings) {
                    if(gbId.equals(supplyMerchantHasGroupBuilding.gettGroupBuildingId())) {//
                        hasGb = false;
                    }
                }
                if(hasGb) {
                    EbuySupplyMerchantHasGroupBuilding supplyMerchantHasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
                    supplyMerchantHasGroupBuilding.setId(ids.get(i));
                    supplyMerchantHasGroupBuilding.settEbuySupplyMerchantId(shopId);
                    supplyMerchantHasGroupBuilding.settGroupBuildingId(gbId);
                    addList.add(supplyMerchantHasGroupBuilding);
                }
            }
			if(!DataUtil.isEmpty(addList)) {
				int i = ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuildingBatch(addList);
				if(i > 0) {
					return "成功配置"+i+"个小区！";
				} else {
					return "配置失败!";
				}
			} else {
				return "配置成功！";
			}
        } else {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_has_group_building, gbIds.length);
            for (int i = 0; i < gbIds.length; i++) {
                BigInteger gbId = BigInteger.valueOf(Long.valueOf(gbIds[i]));
                EbuySupplyMerchantHasGroupBuilding supplyMerchantHasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
                supplyMerchantHasGroupBuilding.setId(ids.get(i));
                supplyMerchantHasGroupBuilding.settEbuySupplyMerchantId(shopId);
                supplyMerchantHasGroupBuilding.settGroupBuildingId(gbId);
                addList.add(supplyMerchantHasGroupBuilding);
            }
			if(!DataUtil.isEmpty(addList)) {
				int i = ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuildingBatch(addList);
				if(i > 0) {
					return "成功配置"+i+"个小区！";
				} else {
					return "配置失败!";
				}
			} else {
				return "配置成功！";
			}

        }
	}

    /**
     * 删除配置的 楼下店小区
     * @param shopId
     * @param gbId
     * @return
     */
    public String delStoreRange(BigInteger shopId, BigInteger gbId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("tEbuySupplyMerchantId", shopId);
        paraMap.put("tGroupBuildingId", gbId);
        List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildings = ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paraMap, false);
		paraMap.remove("tGroupBuildingId");//最后一个小区不能删除
		List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildings02 = ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paraMap, false);
        if(!DataUtil.isEmpty(ebuySupplyMerchantHasGroupBuildings02) && ebuySupplyMerchantHasGroupBuildings02.size() > 1) {
			if(!DataUtil.isEmpty(ebuySupplyMerchantHasGroupBuildings) && ebuySupplyMerchantHasGroupBuildings.size() == 1) {
				int i = ebuySupplyMerchantHasGroupBuildingBaseDao.deleteEbuySupplyMerchantHasGroupBuildingLogic(ebuySupplyMerchantHasGroupBuildings.get(0).getId());
				if(i > 0) {
					return "删除成功!";
				} else {
					return "异常，请重试！";
				}
			} else {
				return "信息异常，请联系管理员！";
			}
		} else {
			return "必须保留一个小区！";
		}
    }

    /**
     * 修改装修图 和 店铺详情图
     * @param merchant
     */
    public int updateEbuySupplyMerchantShopPic(EbuySupplyMerchant merchant) {
        return ebuyMerchantDao.updateEbuySupplyMerchantShopPic(merchant);
    }

	/**
	 * 排序置顶
	 * @param prdtId
	 * @return
	 */
	public int updateSortToTop(BigInteger prdtId) {
		return ebuyMerchantDao.updateSortToTop(prdtId);
    }

	/**
	 *获取店铺的停车优惠券可发送数量
	 * @param supplyId
	 * @return
     */
	public Map<String, Object> getCarCouponNums(Long supplyId) {
		return ebuyMerchantDao.getCarCouponNums(supplyId);
	}
}
